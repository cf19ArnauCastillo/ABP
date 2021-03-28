package com.example.abp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abp.Objects.Quedada;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleTimeZone;

public class MapsFragment extends Fragment {
    private static final int MY_REQUEST_INT = 1;
    private DatabaseReference mDatabase;
    ArrayList<Quedada> quedadas = new ArrayList<>();
    private Quedada quedada;
    private final OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {

            mDatabase = FirebaseDatabase.getInstance().getReference("Quedadas");
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    quedadas.clear();
                    //for (DataSnapshot snapshot : ds.getChildren()) {
                        String id = (String) snapshot.child("ID").getValue();
                        String aficion = (String) snapshot.child("Aficion").getValue();
                        String hora = (String) snapshot.child("Horario").getValue();
                        Date horario = null;
                        try {
                            horario = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(hora);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        double lat = (double) snapshot.child("Latitud").getValue();
                        double lon = (double) snapshot.child("Longitud").getValue();
                        quedadas.add(new Quedada(id, horario, lat, lon, aficion));
                        for (int i = 0; i < quedadas.size(); i++) {
                            LatLng pos = new LatLng(quedadas.get(i).getLatitud(), quedadas.get(i).getLongitud());
                            googleMap.addMarker(new MarkerOptions()
                                    .position(pos));
                            googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(LayoutInflater.from(getActivity()), quedadas.get(i).getAficion(), quedadas.get(i).getHorario().toString()));
                        }
                    //}
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}, MY_REQUEST_INT);

                }
                return;
            }else{
                googleMap.setMyLocationEnabled(true);

            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
    public void transfer(Quedada quedada){
        this.quedada = quedada;
    }
}