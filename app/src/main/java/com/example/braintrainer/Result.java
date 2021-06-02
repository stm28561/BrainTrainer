package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView textViewBest;
    TextView textViewUrResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textViewBest = findViewById(R.id.textViewBest);
        textViewUrResult = findViewById(R.id.textViewUrResult);
        Intent intent = getIntent();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int currentBest = preferences.getInt("best", 0);
        int resultThisTime = intent.getIntExtra("amountOfTightAnswers", 0);
        preferences.edit().putInt("best", 0).apply();
        if (currentBest > resultThisTime) {
            textViewBest.setText("Best result: " + currentBest);
            textViewUrResult.setText("Your result: " + resultThisTime);
            preferences.edit().putInt("best", currentBest).apply();
        } else if (currentBest < resultThisTime) {
            textViewBest.setText("Best result: " + resultThisTime);
            textViewUrResult.setText("Your result: " + resultThisTime);
            preferences.edit().putInt("best", resultThisTime).apply();
        }




    }
}