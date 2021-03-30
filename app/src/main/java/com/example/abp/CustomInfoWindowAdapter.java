package com.example.abp;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.Date;
/*
        Hecho por: Cristian Montañés Escobar
        Correo: cf19cristian.montanes@iesjoandaustria.org
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private static final String TAG = "CustomInfoWindowAdapter";
    private LayoutInflater inflater;
    private String aficion;
    private String horario;
    public CustomInfoWindowAdapter(LayoutInflater inflater, String aficion, String horario){
        this.inflater=inflater;
        this.aficion=aficion;
        this.horario=horario;
    }

    @Override
    public View getInfoContents(final Marker m){
        View v = inflater.inflate(R.layout.info_window, null);
        TextView afic = v.findViewById(R.id.aficion);
        TextView hora = v.findViewById(R.id.fecha);
        afic.setText(aficion);
        hora.setText(horario);
        return v;
    }
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }
}
