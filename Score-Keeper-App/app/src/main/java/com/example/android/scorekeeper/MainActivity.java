package com.example.android.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int scoreTeamA = 0, setWonTeamA = 0;
    int scoreTeamB = 0, setWonTeamB = 0;
    String result = "";
    //team A point count method
    public void addPointToTeamA (View view) {
        scoreTeamA = scoreTeamA + 1 ;
        displayForTeamA(scoreTeamA);
    }
    //team B point count method
    public void addPointToTeamB (View view) {
        scoreTeamB = scoreTeamB + 1 ;
        displayForTeamB(scoreTeamB);
    }
    //team A bonus count method
    public void bonusTeamA (View view) {
        scoreTeamA = scoreTeamA + 2 ;
        displayForTeamA(scoreTeamA);
    }
    //team B bonus count method
    public void bonusTeamB (View view) {
        scoreTeamB = scoreTeamB + 2 ;
        displayForTeamB(scoreTeamB);
    }
    public void setCount(View view){
        if(scoreTeamA > scoreTeamB) {
            setWonTeamA = setWonTeamA + 1 ;
            displaySetWonForTeamA(setWonTeamA);
            scoreTeamA = 0;
            scoreTeamB = 0;
            result = "";
            displayForTeamA(scoreTeamA);
            displayForTeamB(scoreTeamB);
            displayResult(result);
        }
        else if(scoreTeamA == scoreTeamB){
            scoreTeamA = 0;
            scoreTeamB = 0;
            result = "";
            displayResult(result);
            displayForTeamA(scoreTeamA);
            displayForTeamB(scoreTeamB);
        }
        else{
            setWonTeamB = setWonTeamB + 1;
            displaySetWonForTeamB(setWonTeamB);
            scoreTeamA = 0;
            scoreTeamB = 0;
            result = "";
            displayResult(result);
            displayForTeamA(scoreTeamA);
            displayForTeamB(scoreTeamB);
        }
    }
    public void resetAll (View view) {
        scoreTeamA = scoreTeamB = 0;
        setWonTeamA = setWonTeamB = 0;
        result = "";
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displaySetWonForTeamA(setWonTeamA);
        displaySetWonForTeamB(setWonTeamB);
        displayResult(result);
    }
    public void endMatch (View view) {
        result = "";
        if(setWonTeamA == setWonTeamB){
            result = "Match Tied!";
            displayResult(result);
        }
        else if(setWonTeamA > setWonTeamB){
            result = "Team A Won The Match!";
            displayResult(result);
        }
        else {
            result = "Team B Won The Match!";
            displayResult(result);
        }
    }
    /**
     * Displays the given setWonForTeamA for Team A.
     */
    public void displaySetWonForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_set_won);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given setWonForTeamA for Team B.
     */
    public void displaySetWonForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_set_won);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given scoreForTeamA for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given scoreForTeamA for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
    public void displayResult(String score) {
        TextView scoreView = (TextView) findViewById(R.id.result);
        scoreView.setText(score);
    }

}
