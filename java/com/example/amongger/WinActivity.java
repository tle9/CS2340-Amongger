package com.example.amongger;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class WinActivity extends Activity {
    private ImageButton contButton;
    private ImageButton exitButton;
    private TextView finalScore;
    private TextView topScore;
    private int storedScore;


    private String storedName;


    private TextView winName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().getInsetsController().hide(WindowInsetsCompat.Type.systemBars());
        contButtonHandler();
        exitButtonHandler();
        displayFinalScore(getIntent().getExtras().getString("score"));
    }


    public void contButtonHandler() {
        contButton = findViewById(R.id.win_continueButton);
        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contiButtonClicked();
            }
        });
    }

    public void contiButtonClicked() {
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);
        finish();
    }

    public void exitButtonHandler() {
        exitButton = findViewById(R.id.win_exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }

    public void displayFinalScore(String score) {
        finalScore = findViewById(R.id.win_FinalScore);
        topScore = findViewById(R.id.win_TopScore);
        int s = Integer.parseInt(score);
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        storedScore = prefs.getInt("stored_score", 0);
        if (s < storedScore) {
            topScore.setText("Top Score: " + storedScore);
            finalScore.setText("Final Score: " + score);
        } else if (s > storedScore) {
            storedScore = s;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("stored_score", storedScore);
            editor.apply();
            topScore.setText("Top Score: " + storedScore);
            finalScore.setText("(☝ ՞ਊ ՞)☝");
        } else {
            topScore.setText("Top Score: " + storedScore);
            finalScore.setText("Final Score: " + score);
        }
    }
}