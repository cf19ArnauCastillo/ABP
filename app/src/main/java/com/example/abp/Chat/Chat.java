package com.example.abp.Chat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.abp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chat extends Fragment {

    EditText escribir;
    Button enviar;
    String escribir1;
    FirebaseDatabase database;
    DatabaseReference myRef;

    ArrayList<Mensaje> Mensajes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        escribir= v.findViewById(R.id.escribir);
        enviar= v.findViewById(R.id.enviar);
        escribir1="";
        Mensajes = new ArrayList<Mensaje>();

        enviar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Mensaje mensaje = new Mensaje(escribir.getText().toString());
                Mensajes.add(mensaje);

                myRef.setValue(Mensajes);
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("logTest " ,""+dataSnapshot.getChildrenCount());

                Mensajes.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Mensaje mensaje = postSnapshot.getValue(Mensaje.class);
                    Mensajes.add(mensaje);
                    Log.i("logTest",mensaje.getmensaje());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("logTest", "Failed to read value.", error.toException());
            }
        });
        return v;
    }
}