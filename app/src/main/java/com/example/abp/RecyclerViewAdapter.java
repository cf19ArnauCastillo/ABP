package com.example.abp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private String [] array_noms;
    private Context context;

    public RecyclerViewAdapter(Context con, String[] arrN){
        array_noms = arrN;
        context = con;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.NombreUsuario.setText(array_noms[position]);
    }

    @Override
    public int getItemCount() {
        return array_noms.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView NombreUsuario;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NombreUsuario = itemView.findViewById(R.id.UserName);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}

