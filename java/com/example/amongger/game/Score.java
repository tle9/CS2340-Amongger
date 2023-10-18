package com.example.amongger.game;

//import android.content.Context;

import android.widget.TextView;


public class Score {
    private int multiplier;
    private int[] score;
    private int currentScore;
    private TextView scoreText;
    private String scoreOutput;
    public Score(String lvl, TextView scoreView) {
        if (lvl.equals("Difficulty: Level 1")) {
            this.multiplier = 2;
            levelOneScoreLayout();
        } else if (lvl.equals("Difficulty: Level 2")) {
            this.multiplier = 4;
            levelTwoScoreLayout();
        } else {
            this.multiplier = 10;
            levelHmmmScoreLayout();
        }
        this.currentScore = 0;
        this.scoreText = scoreView;
    }

    /**
     * Row 0 - 20 | 21 Tiles
     * Topdown order
     */
    private void levelOneScoreLayout() {
        score = new int[] {0, 0, 0, 0, 260, 130,
            120, 110, 100, 90, 80, 70,
            0, 0, 60, 50, 40, 30,
            20, 10, 0, 0};
    }
    private void levelTwoScoreLayout() {
        score = new int[] {0, 0, 0, 0, 300, 280,
            260, 240, 220, 200, 180, 160,
            140, 120, 100, 0, 80, 60,
            40, 20, 0, 0};
    }
    private void levelHmmmScoreLayout() {
        score = new int[] {0, 0, 0, 0, 600, 300,
            280, 260, 240, 0, 220, 200,
            180, 160, 140, 120, 100, 80,
            60, 40, 20, 0};
    }

    public int getRowPoint(int row) {
        if (score[row] != 0) {
            currentScore += (score[row] * multiplier);
        }
        scoreOutput = "Score: " + currentScore;
        scoreText.setText(scoreOutput);
        return currentScore;
    }
    public void resetScore() {
        currentScore = 0;
        scoreOutput = "Score: " + currentScore;
        scoreText.post(new Runnable() {
            @Override
            public void run() {
                scoreText.setText(scoreOutput);
            }
        });
    }
    public int getScoreTest() {
        return currentScore;
    }
    public void scoreResetTest() {
        currentScore = 0;
    }
}
