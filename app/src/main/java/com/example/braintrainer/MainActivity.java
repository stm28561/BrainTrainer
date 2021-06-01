package com.example.braintrainer;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textViewTimer;
    TextView textViewTest;
    Button buttonAnswer1;
    Button buttonAnswer2;
    Button buttonAnswer3;
    Button buttonAnswer4;
    private int resultOfCalculation;
    Calculations calculations = new Calculations();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTimer = findViewById(R.id.textViewTimer);
        textViewTest = findViewById(R.id.textViewTest);
        buttonAnswer1 = findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = findViewById(R.id.buttonAnswer3);
        buttonAnswer4 = findViewById(R.id.buttonAnswer4);

        calculations.execute();
        CountDownTimer timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) millisUntilFinished / 1000;
                seconds++;
                textViewTimer.setText(Integer.toString(seconds));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Timer is finished", Toast.LENGTH_SHORT).show();
                textViewTimer.setText(Integer.toString(0));
            }

        };
        timer.start();
    }

    public void onClickAnswer1(View view) {
        if (Integer.parseInt(String.valueOf(buttonAnswer1.getText())) == resultOfCalculation) {
            calculations.execute();
        } else calculations.execute();
    }

    public void onClickAnswer2(View view) {
        if (Integer.parseInt(String.valueOf(buttonAnswer1.getText())) == resultOfCalculation) {
            calculations.execute();
        } else calculations.execute();
    }

    public void onClickAnswer3(View view) {
        if (Integer.parseInt(String.valueOf(buttonAnswer1.getText())) == resultOfCalculation) {
            calculations.execute();
        } else calculations.execute();
    }

    public void onClickAnswer4(View view) {
        if (Integer.parseInt(String.valueOf(buttonAnswer1.getText())) == resultOfCalculation) {
            calculations.execute();
        } else calculations.execute();
    }


    public class Calculations extends AsyncTask<Integer, Void, Integer> {


        private String test;
        private int wrongResult1;
        private int wrongResult2;
        private int wrongResult3;
        ArrayList<Integer> randomAnswerArray = new ArrayList<>(4);

        public String randomTest () {
            int numberOne = (int) (Math.random() * 100 + 1);
            int numberTwo = (int) (Math.random() * 100 + 1);
            int mathematicalOperation = (int) (Math.random() * 2 + 1);
            switch (mathematicalOperation) {
                case (1):
                    resultOfCalculation = numberOne + numberTwo;
                    return numberOne + " + " + numberTwo;
                case (2):
                    resultOfCalculation = numberOne - numberTwo;
                    return numberOne + " - " + numberTwo;
            }
            return null;
        }

        public void fillRandomArray () {
            randomAnswerArray.add(resultOfCalculation);
            randomAnswerArray.add(wrongResult1);
            randomAnswerArray.add(wrongResult2);
            randomAnswerArray.add(wrongResult3);
        }

        public int getRandomIntFromArray () {
            int elementOfArray = (int) (Math.random() * randomAnswerArray.size());
            int toReturn = randomAnswerArray.get(elementOfArray);
            randomAnswerArray.remove(elementOfArray);
            return toReturn;
        }


        public void threeWrongResults () {
            wrongResult1 = (int) (Math.random() * resultOfCalculation - 50 + 50);
            wrongResult2 = (int) (Math.random() * resultOfCalculation - 50 + 50);
            wrongResult3 = (int) (Math.random() * resultOfCalculation - 50 + 50);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            test = randomTest();
            threeWrongResults();
            fillRandomArray();
            Log.i("Calc", "" + resultOfCalculation);
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            textViewTest.setText(test);
            buttonAnswer1.setText(Integer.toString(getRandomIntFromArray()));
            buttonAnswer2.setText(Integer.toString(getRandomIntFromArray()));
            buttonAnswer3.setText(Integer.toString(getRandomIntFromArray()));
            buttonAnswer4.setText(Integer.toString(getRandomIntFromArray()));
        }
    }

}