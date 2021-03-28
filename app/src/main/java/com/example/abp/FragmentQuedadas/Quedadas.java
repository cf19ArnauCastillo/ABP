package com.example.abp.FragmentQuedadas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Quedadas extends Fragment {
    RecyclerView rv;
    List<Quedada> quedadas;

    com.example.abp.FragmentQuedadas.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quedadas, container, false);
        rv = (RecyclerView) v.findViewById(R.id.recyclerviewQuedadas);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        quedadas = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter = new Adapter(quedadas);

        rv.setAdapter(adapter);

        database.getReference().child("Quedadas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    quedadas.removeAll(quedadas);
                    //for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    String id = (String) snapshot.child("ID").getValue();
                    String aficion = (String) snapshot.child("Aficion").getValue();
                    String hora = (String) snapshot.child("Horario").getValue();
                    Date horario = null;
                    try {
                        horario = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(hora);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    double lat = (double) snapshot.child("Latitud").getValue();
                    double lon = (double) snapshot.child("Longitud").getValue();
                    quedadas.add(new Quedada(id, horario, lat, lon, aficion));
                    adapter.notifyDataSetChanged();
                    //}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }

}