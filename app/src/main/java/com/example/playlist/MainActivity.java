package com.example.playlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Musica> myPlayList = new ArrayList<>();
        createPlayList(myPlayList);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdaptadorPlayList(myPlayList));

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Detectamos el giro de pantalla y así cambiar el tipo de Layout del RecyclerView
        // Se ha añadido para la activity en el Manifest:   android:configChanges="orientation|screenSize"
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void createPlayList(List<Musica> myPlayList) {
        myPlayList.add(new Musica(1, "Tocata y fuga en Re menor", "Johann Sebastian Bach", R.drawable.img, R.raw.say_my_grace));
        myPlayList.add(new Musica(2, "Marcha Turca", "Wolfgang Amadeus Mozart", R.drawable.img, R.raw.say_my_grace));
        myPlayList.add(new Musica(3, "Novena sinfonía Mov.1", "Ludwig van Beethoven", R.drawable.img, R.raw.say_my_grace));
        myPlayList.add(new Musica(4, "Tocata y fuga en Re menor", "Johann Sebastian Bach", R.drawable.img, R.raw.say_my_grace));
        myPlayList.add(new Musica(5, "Marcha Turca", "Wolfgang Amadeus Mozart", R.drawable.img, R.raw.say_my_grace));
        myPlayList.add(new Musica(6, "Tocata y fuga en Re menor", "Johann Sebastian Bach", R.drawable.img, R.raw.say_my_grace));
        myPlayList.add(new Musica(7, "Tocata y fuga en Re menor", "Johann Sebastian Bach", R.drawable.img, R.raw.say_my_grace));
        myPlayList.add(new Musica(8, "Marcha Turca", "Wolfgang Amadeus Mozart", R.drawable.img, R.raw.say_my_grace));
        myPlayList.add(new Musica(9, "Marcha Turca", "Wolfgang Amadeus Mozart", R.drawable.img, R.raw.say_my_grace));
        myPlayList.add(new Musica(10, "Marcha Turca", "Wolfgang Amadeus Mozart", R.drawable.img, R.raw.say_my_grace));
        myPlayList.add(new Musica(11, "Novena sinfonía Mov.1", "Ludwig van Beethoven", R.drawable.img, R.raw.say_my_grace));
        myPlayList.add(new Musica(12, "Tocata y fuga en Re menor", "Johann Sebastian Bach", R.drawable.img, R.raw.say_my_grace));
    }
}