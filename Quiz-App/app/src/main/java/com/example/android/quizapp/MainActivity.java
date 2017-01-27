package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * emailField is used for store the email address of user
     * nameField is used for store the name of the user.
     */
    String emailField = "", nameField = "";

    public void startQuizQuestion(View view) {
        emailField = ((EditText) findViewById(R.id.email_text_field)).getText().toString();
        nameField = ((EditText) findViewById(R.id.name_text_field)).getText().toString();
        Intent intent = new Intent(MainActivity.this, quiz_question.class);
        intent.putExtra("nameField", nameField);
        startActivity(intent);
    }
}
