package com.example.abp.Chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    ArrayList<mensaje> mensajes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        escribir=(EditText) getView().findViewById(R.id.escribir);
        enviar=(Button) getView().findViewById(R.id.enviar);
        escribir1="";
        mensajes = new ArrayList<mensaje>();

        enviar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mensaje mensaje = new mensaje(escribir.getText().toString());
                mensajes.add(mensaje);

                myRef.setValue(mensajes);
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("logTest " ,""+dataSnapshot.getChildrenCount());

                mensajes.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    mensaje mensaje = postSnapshot.getValue(mensaje.class);
                    mensajes.add(mensaje);
                    Log.i("logTest",mensaje.getmensaje());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("logTest", "Failed to read value.", error.toException());
            }
        });
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}