package com.example.abp.Chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.ArrayList;

public class RecyclerNombreUsuarios extends RecyclerView.Adapter<RecyclerNombreUsuarios.ViewHolder>{
    private ArrayList<Usuario> Usuarios;
    ArrayList<Mensaje> mensajes;
    Context con;
    DatabaseReference myRef;

    public RecyclerNombreUsuarios(ArrayList<Usuario> arrayUsuarios, Context con) {
        this.Usuarios = arrayUsuarios;
        this.con = con;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list, parent, false);
        ViewHolder holder = new ViewHolder(view2);

        return holder;
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder,int position){
        holder.btnUsuario.setText(Usuarios.get(position).getNombre());

        holder.btnUsuario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.i("logTest " ,""+dataSnapshot.getChildrenCount());

                        mensajes.clear();

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Mensaje mensaje = postSnapshot.getValue(Mensaje.class);
                            mensajes.add(mensaje);
                            Log.i("logTest",mensaje.getmensaje());
                        }
                        RecyclerView recycler= ((Chat) con).findViewById(R.id.bocadilloschat);
                        recycler.setAdapter(new RecyclerBocadillosChat(mensajes));
                        recycler.setLayoutManager(new LinearLayoutManager((/*context*/con)));
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.i("logTest", "Failed to read value.", error.toException());
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() { return Usuarios.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected Button btnUsuario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnUsuario = itemView.findViewById(R.id.UsuariosBtt); }
    }


}