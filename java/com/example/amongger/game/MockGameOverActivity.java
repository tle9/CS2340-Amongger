package com.example.amongger.game;

public class MockGameOverActivity {
    private boolean isFinished = false;
    private boolean isPressed = false;
    private String state;
    private int score = 0;
    private int gameScore = 0;

    public void contiButtonClicked() {
        state = "ConfigActivity.java";
    }

    public void exitButtonClicked() {
        state = "android.os.Process.killProcess(android.os.Process.myPid())";
    }

    public void noButtonClicked() {
        state = "GameOverActivity.java";
    }


    public String retrieveActivityState() {
        return state;
    }

    public boolean finish() {
        if (state.equals("ConfigActivity.java")
                || state.equals("android.os.Process.killProcess(android.os.Process.myPid())")) {
            return true;
        } else {
            return isFinished;
        }
    }

    public boolean scoreRetriveFromGameAct() {
        return score == gameScore;
    }

    public void editScoreFromGameAct(int score) {
        if (score < 0) {
            this.gameScore = 0;
        } else {
            this.gameScore = score;
        }
    }

    public void editScoreTest(int score) {
        if (score < 0) {
            this.score = 0;
        } else {
            this.score = score;
        }
    }

    public int retrieveScore() {
        return score;
    }
}
