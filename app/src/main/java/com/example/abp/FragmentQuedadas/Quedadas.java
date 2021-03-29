package com.example.abp.FragmentQuedadas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.abp.Objects.Quedada;
import com.example.abp.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Quedadas extends Fragment {
    RecyclerView rv;
    ArrayList<Quedada> quedadas = new ArrayList<Quedada>();
    Button add;
    private DatabaseReference mDatabase;

    com.example.abp.FragmentQuedadas.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quedadas, container, false);
        rv = (RecyclerView) v.findViewById(R.id.recyclerviewQuedadas);
        add = v.findViewById(R.id.new_quedada);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        mDatabase = FirebaseDatabase.getInstance().getReference("Quedadas");

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                quedadas.clear();
                for (DataSnapshot ps:datasnapshot.getChildren()){
                    Quedada quedada = new Quedada((String)ps.child("id").getValue(),(String)ps.child("horario").getValue(),(double)ps.child("latitud").getValue(), (double)ps.child("longitud").getValue(), (String)ps.child("aficion").getValue());
                    quedadas.add(quedada);
                    }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new Adapter(quedadas);
        rv.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("CREAR QUEDADA");
                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                final EditText aficion = new EditText(getContext());
                aficion.setHint("Aficion");
                layout.addView(aficion);
                final EditText horario = new EditText(getContext());
                horario.setHint("Horario (dd/MM/yyyy hh/MM/ss)");
                layout.addView(horario);
                final EditText localizacion = new EditText(getContext());
                layout.addView(localizacion);
                localizacion.setHint("Direccion");

                alert.setPositiveButton("CREAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double lat = 0, lng = 0;
                        String hora = horario.getText().toString();
                        String af = aficion.getText().toString();
                        Geocoder coder = new Geocoder(getContext());
                        String loc = localizacion.getText().toString();
                        try {
                            List<Address> addressList = coder.getFromLocationName(loc, 1);
                            lat = addressList.get(0).getLatitude();
                            lng = addressList.get(0).getLongitude();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Quedada meet = new Quedada("001", hora, lat, lng, af);
                        quedadas.add(meet);
                        mDatabase.setValue(quedadas);
                    }
                });
                alert.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.setView(layout);
                alert.show();
            }
        });

        return v;
    }

}