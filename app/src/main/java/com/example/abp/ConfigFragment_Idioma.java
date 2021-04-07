package com.example.abp;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Locale;

/*
        Hecho por: Cristian Montañés Escobar
        Correo: cf19cristian.montanes@iesjoandaustria.org
 */

public class ConfigFragment_Idioma extends Fragment {

    public ConfigFragment_Idioma() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_config__idioma, container, false);
        Button esp = v.findViewById(R.id.Espanol);
        Button cat = v.findViewById(R.id.Catalan);
        Button eng = v.findViewById(R.id.English);

        esp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppLocale("es");
            }
        });
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppLocale("ca");
            }
        });
        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppLocale("en");
            }
        });


        // Inflate the layout for this fragment
        return v;
    }
    public void setAppLocale(String localcode){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(localcode.toLowerCase()));
        }else {
            conf.locale = new Locale(localcode.toLowerCase());
        }
        res.updateConfiguration(conf, dm);
    }
}