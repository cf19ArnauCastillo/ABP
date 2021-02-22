package com.example.abp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abp.Objects.Quedada;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsFragment extends Fragment {
    private DatabaseReference RefQuedadas;
    private ArrayList<Quedada> Quedadas;
    double Lat;
    double lon;

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Quedadas.clear();
            if (dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Quedada quedada = snapshot.getValue(Quedada.class);
                    Quedadas.add(quedada);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

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

        RefQuedadas = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Quedadas")
                .orderByChild("id");
        RefQuedadas.addListenerForSingleValueEvent(valueEventListener);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            MarkerOptions markerOptions = new MarkerOptions();
            @Override
            public void onMapReady(GoogleMap googleMap) {
                for (int i = 0; i == Quedadas.size(); i++){
                    Lat = Quedadas.get(i).getLatitud();
                    lon = Quedadas.get(i).getLongitud();
                    LatLng location = new LatLng(Lat, lon);
                    markerOptions.position(location);
                }

            }
        });
    }
}