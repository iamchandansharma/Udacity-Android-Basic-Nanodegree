package com.android.example.booklisting;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String userInput=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final boolean networkStatus = isNetworkAvailable();
        Button search = (Button) findViewById(R.id.search_button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInput = ((EditText) findViewById(R.id.book_search)).getText().toString().replace(" ", "");
                if (networkStatus) {
                    if (userInput.equals("")) {
                        Toast.makeText(MainActivity.this, "Please Enter Book Name!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent sendUserInput = new Intent(MainActivity.this, BookList.class);
                        sendUserInput.putExtra("input", userInput);
                        ((EditText) findViewById(R.id.book_search)).setText("");
                        startActivity(sendUserInput);
                    }
                }
                else
                    Toast.makeText(MainActivity.this, "Please Check Your Internet Connection!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
