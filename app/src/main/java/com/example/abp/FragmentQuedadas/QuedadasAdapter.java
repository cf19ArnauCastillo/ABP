package com.example.abp.FragmentQuedadas;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abp.MapsFragment;
import com.example.abp.Objects.Quedada;
import com.example.abp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*
        Hecho por: Cristian Montañés Escobar
        Correo: cf19cristian.montanes@iesjoandaustria.org
 */
class Adapter extends RecyclerView.Adapter<Adapter.QuedadasviewHolder> {
    ArrayList<Quedada> quedadas;

    public Adapter(ArrayList<Quedada> quedadas){
        this.quedadas = quedadas;
    }
    @NonNull
    @Override
    public QuedadasviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quedadas_item_list,parent,false);
        QuedadasviewHolder holder = new QuedadasviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuedadasviewHolder holder, int position) {
        Log.i("logtest", quedadas.size()+"");
        holder.aficion.setText(quedadas.get(position).getAficion());
        holder.horario.setText(quedadas.get(position).getHorario());
        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return quedadas.size();
    }

    public static class QuedadasviewHolder extends RecyclerView.ViewHolder{
        TextView aficion, horario;
        Button location;
        public QuedadasviewHolder(View itemView){
            super(itemView);
            aficion = itemView.findViewById(R.id.Aficion);
            horario = itemView.findViewById(R.id.horario);
            location = itemView.findViewById(R.id.map_transfer);
        }
    }

}