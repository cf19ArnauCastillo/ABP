package com.example.abp.FragmentQuedadas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abp.Objects.Quedada;
import com.example.abp.R;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuedadasAdapter extends RecyclerView.Adapter<QuedadasAdapter.ViewHolder> implements View.OnClickListener {
    ArrayList<Quedada> lista;

    public QuedadasAdapter(ArrayList<Quedada> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quedadas_item_list, null,  false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.aficion.setText(lista.get(position).getAficion());
        holder.horario.setText(lista.get(position).getHorario().toString());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        
        Button acceder;
        TextView aficion;
        TextView horario;
        MapView mapView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            acceder = itemView.findViewById(R.id.acceder);
            aficion = itemView.findViewById(R.id.Aficion);
            horario = itemView.findViewById(R.id.horario);
            mapView = itemView.findViewById(R.id.Location);
        }
    }
}
