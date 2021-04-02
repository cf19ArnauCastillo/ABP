package com.example.abp.Chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abp.R;

import java.util.ArrayList;

public class RecyclerNombreUsuarios extends RecyclerView.Adapter<RecyclerNombreUsuarios.ViewHolder>{

    private ArrayList<Usuario> Usuarios;

    public RecyclerNombreUsuarios(ArrayList<Usuario> arrayUsuarios) {
        this.Usuarios = arrayUsuarios;
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
        holder.Usuarios.setText(Usuarios.get(position).getNombre());
    }

    @Override
    public int getItemCount () {
        return Usuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button Usuarios;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Usuarios = itemView.findViewById(R.id.Usuarios); }
    }
}