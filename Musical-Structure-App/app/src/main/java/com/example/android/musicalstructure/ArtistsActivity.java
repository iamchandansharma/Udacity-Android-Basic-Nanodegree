package com.example.android.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ArtistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);
        // Find the View that shows the artists category
        TextView artistOne = (TextView) findViewById(R.id.atif_album);

        // Set a click listener on that View
        artistOne.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent artistsIntent = new Intent(ArtistsActivity.this, SongsActivity.class);
                startActivity(artistsIntent);
            }
        });

        // Find the View that shows the albums category
        TextView artistTwo = (TextView) findViewById(R.id.sherya_album);

        // Set a click listener on that View
        artistTwo.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(ArtistsActivity.this, SongsActivity.class);
                startActivity(albumsIntent);
            }
        });

        // Find the View that shows the songs category
        TextView artistThree = (TextView) findViewById(R.id.sonu_album);

        // Set a click listener on that View
        artistThree.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(ArtistsActivity.this, SongsActivity.class);
                startActivity(songsIntent);
            }
        });

        // Find the View that shows the artists category
        TextView artistFour = (TextView) findViewById(R.id.arjeet_album);

        // Set a click listener on that View
        artistFour.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent favoritesIntent = new Intent(ArtistsActivity.this, SongsActivity.class);
                startActivity(favoritesIntent);
            }
        });
    }
}
