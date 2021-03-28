package com.example.abp.Chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abp.R;

import java.util.ArrayList;

public class RecyclerBocadillosChat extends RecyclerView.Adapter<RecyclerBocadillosChat.ViewHolder>{

    private ArrayList<Mensaje> Mensajes;

    public RecyclerBocadillosChat(ArrayList<Mensaje> arrayMissatges ){
        this.Mensajes = arrayMissatges;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.etiquetaMissatge.setText(Mensajes.get(position).getmensaje());
    }

    @Override
    public int getItemCount() {
        return Mensajes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView etiquetaMissatge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etiquetaMissatge = itemView.findViewById(R.id.txtMisatge);
        }
    }

}