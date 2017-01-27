package com.example.android.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Find the View that shows the songOne category
        TextView songOne = (TextView) findViewById(R.id.song_01);

        // Set a click listener on that View
        songOne.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(FavoritesActivity.this, SongMain.class);
                startActivity(songsIntent);
            }
        });
        // Find the View that shows the songTwo category
        TextView songTwo = (TextView) findViewById(R.id.song_02);

        // Set a click listener on that View
        songTwo.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(FavoritesActivity.this, SongMain.class);
                startActivity(songsIntent);
            }
        });
        // Find the View that shows the songThree category
        TextView songThree = (TextView) findViewById(R.id.song_03);

        // Set a click listener on that View
        songThree.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(FavoritesActivity.this, SongMain.class);
                startActivity(songsIntent);
            }
        });
        // Find the View that shows the songFour category
        TextView songFour = (TextView) findViewById(R.id.song_04);

        // Set a click listener on that View
        songFour.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(FavoritesActivity.this, SongMain.class);
                startActivity(songsIntent);
            }
        });
    }
}
