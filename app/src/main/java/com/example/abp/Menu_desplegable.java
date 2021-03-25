package com.example.abp;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abp.Objects.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class Menu_desplegable extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ArrayList<String> correos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_desplegable);
        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.quedadas, R.id.crearQuedada, R.id.mapsFragment, R.id.chat, R.id.configFragment_Idioma, R.id.configFragment_AboutUs, R.id.configFragment_Ajuda)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.contenedor);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*FirebaseUser user = mAuth.getCurrentUser();
        TextView nombre = findViewById(R.id.name);
        TextView mail = findViewById(R.id.mail);
        ImageView photo = findViewById(R.id.foto);

        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        nombre.setText(name);
        mail.setText(email);
        photo.setImageURI(photoUrl);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference Usuario = mDatabase.child("Usuario");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    String correo = ds.getKey();
                    correos.add(correo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        Usuario.addListenerForSingleValueEvent(eventListener);
        boolean detector = true;
        int tamano = correos.size();
        for (int i = 0;i==tamano;i++){
            String correo = correos.get(i);
            if (correo == email){
                break;
            }
            if (correo != email && i == tamano) {
                detector = false;
            }
        }
        if (detector = false){
            User usuario = new User(name, email);
            mDatabase.child("Usuario").setValue(usuario);
        }*/
    }
    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_desplegable, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp(){
        NavController navController = Navigation.findNavController(this, R.id.contenedor);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                ||super.onSupportNavigateUp();
    }
}