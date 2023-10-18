package com.example.amongger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amongger.game.Score;
import com.example.amongger.game.TouchInput;

/**
 * Sprint2 Junit tests.
 */
public class GameActivity extends AppCompatActivity {

    //Tilemap & whole surface drawing layer.
    private GameSurface gameView;

    //Display resolution capture.
    private DisplayMetrics gameDisplay;
    private int displayWidth;
    private int displayHeight;
    //Local Variables
    private TextView playerNameView;
    private TextView difficultyView;
    private TextView livesView;
    private TextView scoreView;
    private String difficulty;
    private String playerLives;
    private int score = 0;
    private Score scoreHandler;

    private MediaPlayer mediaPlayer;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);
        //Fullscreen. Removes title bar and hides navigation bar at the bottom.
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().getInsetsController().hide(WindowInsetsCompat.Type.systemBars());
        //Root layout of activity_game4.xml
        ConstraintLayout rootLayout = findViewById(R.id.mainLayout);
        //Loads display metrics captured from device.
        initializeDisplay();
        initializeViews();
        initializeDifficultyProfile(getIntent().getExtras().getInt("difficulty"));
        scoreHandler = new Score(difficulty, scoreView);
        initializeGameSurface(getIntent().getExtras().getInt("color"), scoreHandler,
            livesView);
        //Ensures gameView is loaded first in layout hierarchy.
        rootLayout.addView(gameView, 0);
        initializeOutputTextDisplay(getIntent().getExtras().getString("name"), gameView);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.space_theme);
        mediaPlayer.start();
        //Game Controller based on User Touch Inputs via tap or gestures.
        gameView.setOnTouchListener(new TouchInput(this) {
            @Override
            public void up() {
                gameView.updatePlayerPosition('U');
                gameView.movePlayer();
            }
            @Override
            public void left() {
                gameView.updatePlayerPosition('L');
                gameView.movePlayer();
            }
            @Override
            public void right() {
                gameView.updatePlayerPosition('R');
                gameView.movePlayer();
            }
            @Override
            public void down() {
                gameView.updatePlayerPosition('D');
                gameView.movePlayer();
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_G:
            return true;
        case KeyEvent.KEYCODE_W:
            gameView.updatePlayerPosition('U');
            gameView.movePlayer();
            return true;
        case KeyEvent.KEYCODE_A:
            gameView.updatePlayerPosition('L');
            gameView.movePlayer();
            return true;
        case KeyEvent.KEYCODE_D:
            gameView.updatePlayerPosition('R');
            gameView.movePlayer();
            return true;
        case KeyEvent.KEYCODE_S:
            gameView.updatePlayerPosition('D');
            gameView.movePlayer();
            return true;
        default:
            return super.onKeyUp(keyCode, event);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        gameView.pause();
    }
    @Override
    public void onResume() {
        super.onResume();
        gameView.resume();
    }

    /**
     * Captures the Display Resolution. Used for resizing game map and sprites accordingly.
     */
    private void initializeDisplay() {
        gameDisplay = getResources().getDisplayMetrics();
        displayWidth = gameDisplay.widthPixels;
        displayHeight = gameDisplay.heightPixels;
    }

    public void initializeGameSurface(int selectedColor, Score scoreSystem, TextView lv) {
        gameView = new GameSurface(this, displayWidth, displayHeight, difficulty,
            selectedColor, scoreSystem, lv);
    }
    /**
     * Loads TextViews that will be used to display player info.
     */
    private void initializeViews() {
        playerNameView = findViewById(R.id.nameView);
        difficultyView = findViewById(R.id.difficultyView);
        livesView = findViewById(R.id.livesView);
        scoreView = findViewById(R.id.scoreView);
    }

    /**
     * Sets difficulty string based on selected Difficulty.
     * Sets lives and scoreMultiplier depending on Difficulty.
     * @param difficultyLevel selected at ConfigActivity
     */
    private void initializeDifficultyProfile(int difficultyLevel) {
        if (difficultyLevel == 0) {
            difficulty = "Difficulty: Level 1";
        } else if (difficultyLevel == 1) {
            difficulty = "Difficulty: Level 2";
        } else {
            difficulty = "Difficulty: Hmmm";
        }
    }


    /**
     * Sets the text for TextViews from initializeViews except for scoreView.
     * scoreView should remain as is until updated by player movement.
     * @param name inputted at ConfigActivity
     * @param gV gV
     */
    private void initializeOutputTextDisplay(String name, GameSurface gV) {
        playerNameView.setText(name);
        difficultyView.setText(difficulty);
        gV.playerLifeUpdate();
    }

    private void initGameOverScreen() {
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra("score", this.score);
        startActivity(intent);
        finish();
    }

    private void initWinScreen() {
        Intent intent = new Intent(this, WinActivity.class);
        intent.putExtra("score", this.score);
        startActivity(intent);
        finish();
    }
}



