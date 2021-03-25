package com.example.abp.FragmentQuedadas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abp.Objects.Quedada;
import com.example.abp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Quedadas extends Fragment {
    ArrayList<Quedada> lista;
    RecyclerView mRecyclerView;
    private DatabaseReference RefQuedadas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRecyclerView = mRecyclerView.findViewById(R.id.recyclerviewQuedadas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lista = new ArrayList<Quedada>();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lista.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Quedada quedada = snapshot.getValue(Quedada.class);
                        lista.add(quedada);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        RefQuedadas = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Quedadas")
                .orderByChild("id");
        RefQuedadas.addListenerForSingleValueEvent(valueEventListener);

        QuedadasAdapter adapter = new QuedadasAdapter(lista);
        mRecyclerView.setAdapter(adapter);

        return inflater.inflate(R.layout.fragment_quedadas, container, false);
    }
}