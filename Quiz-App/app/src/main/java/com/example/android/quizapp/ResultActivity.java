package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle bundle = getIntent().getExtras();
        String result = bundle.getString("result");
        int resultValue = bundle.getInt("resultValue");
        TextView textView = (TextView) findViewById(R.id.result_text_view);
        textView.setText(result);
        Toast.makeText(this, "Your Result is: " + resultValue, Toast.LENGTH_SHORT).show();
    }
}
