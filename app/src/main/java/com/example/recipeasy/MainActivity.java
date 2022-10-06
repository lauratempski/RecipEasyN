package com.example.recipeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.recipeasy.Adapter.RandomRecipeAdapter;
import com.example.recipeasy.Listener.RandomRecipeResponseListener;
import com.example.recipeasy.Models.RandomRecipeAPIResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView nav;

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

    }

    public void initUI(){
        setContentView(R.layout.activity_main);

        nav = (BottomNavigationView) findViewById(R.id.navigation);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.homeIcon:
                        startActivity(new Intent(MainActivity.this,Home.class));
                        break;

                    case R.id.favIcon:
                        startActivity(new Intent(MainActivity.this, Favourites.class));
                        break;


                    case R.id.addRecipeIcon:
                        startActivity(new Intent(MainActivity.this, AddRecipe.class));
                        break;


                    case R.id.listIcon:
                        startActivity(new Intent(MainActivity.this,ShoppigList.class));
                        break;

                    case R.id.scannerIcon:
                        startActivity(new Intent(MainActivity.this,BarCodeScanner.class));
                        break;
                }
                return false;
            }
        });

    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeAPIResponse response, String message) {
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            adapter = new RandomRecipeAdapter(MainActivity.this, response.recipes);
            recyclerView.setAdapter(adapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

}