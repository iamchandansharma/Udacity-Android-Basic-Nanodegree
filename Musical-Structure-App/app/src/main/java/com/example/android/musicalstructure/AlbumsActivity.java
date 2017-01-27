package com.example.android.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AlbumsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        // Find the View that shows the albumOne category
        TextView albumOne = (TextView) findViewById(R.id.album_one);

        // Set a click listener on that View
        albumOne.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(AlbumsActivity.this, SongsActivity.class);
                startActivity(albumsIntent);
            }
        });

        // Find the View that shows the albumTwo category
        TextView albumTwo = (TextView) findViewById(R.id.album_two);

        // Set a click listener on that View
        albumTwo.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(AlbumsActivity.this, SongsActivity.class);
                startActivity(albumsIntent);
            }
        });

        // Find the View that shows the albumThree category
        TextView albumThree = (TextView) findViewById(R.id.album_three);

        // Set a click listener on that View
        albumThree.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(AlbumsActivity.this, SongsActivity.class);
                startActivity(albumsIntent);
            }
        });

        // Find the View that shows the albumFour category
        TextView albumFour = (TextView) findViewById(R.id.album_four);

        // Set a click listener on that View
        albumFour.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(AlbumsActivity.this, SongsActivity.class);
                startActivity(albumsIntent);
            }
        });

        // Find the View that shows the albumFive category
        TextView albumFive = (TextView) findViewById(R.id.album_five);

        // Set a click listener on that View
        albumFive.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(AlbumsActivity.this, SongsActivity.class);
                startActivity(albumsIntent);
            }
        });
        // Find the View that shows the albumSix category
        TextView albumSix = (TextView) findViewById(R.id.album_six);

        // Set a click listener on that View
        albumSix.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(AlbumsActivity.this, SongsActivity.class);
                startActivity(albumsIntent);
            }
        });
    }
}
