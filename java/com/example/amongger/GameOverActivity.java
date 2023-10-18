package com.example.amongger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameOverActivity extends AppCompatActivity {
    private ImageButton contButton;
    private ImageButton exitButton;
    private TextView finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        //Fullscreen. Removes title bar and hides navigation bar at the bottom.
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().getInsetsController().hide(WindowInsetsCompat.Type.systemBars());
        contButtonHandler();
        exitButtonHandler();
        displayFinalScore(getIntent().getExtras().getString("score"));
    }


    public void contButtonHandler() {
        contButton = findViewById(R.id.gameover_continueButton);
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
        exitButton = findViewById(R.id.gameover_exitButton);
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
        finalScore = findViewById(R.id.gameover_FinalScore);
        finalScore.setText(score);
    }
}

