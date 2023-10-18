package com.example.amongger.game;

public class MockWinActivity {
    private boolean isFinished = false;
    private boolean isPressed = false;
    private String state;
    private int score = 0;
    private int gameScore = 0;
    private int topScore = 0;

    private String topScoreTextView;
    private String playerScoreTextView;

    public void contiButtonClicked() {
        state = "ConfigActivity.java";
    }

    public void exitButtonClicked() {
        state = "android.os.Process.killProcess(android.os.Process.myPid())";
    }

    public void noButtonClicked() {
        state = "WinActivity.java";
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

    public boolean scoreRetrieveFromGameAct() {
        return score == gameScore;
    }

    public void displayTopScore(int score, int topScore) {
        if (score < topScore) {
            topScoreTextView = "Top Score: " + topScore;
            playerScoreTextView = "Your Score: " + score;
        } else if (score > topScore) {
            topScore = score;
            topScoreTextView = "Top Score: " + topScore;
            playerScoreTextView = null;
        } else {
            topScoreTextView = "Top Score: " + topScore;
            playerScoreTextView = "Your Score: " + score;
        }
    }

    public boolean scoreRetrieveAndDisplayCorrectly() {
        return score == topScore;
    }

    public void editTopScore(int score) {
        if (topScore < this.gameScore) {
            this.topScore = score;
        } else {
            this.topScore = topScore;
        }
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

    public String retrieveTopScoreTextView() {
        return topScoreTextView;
    }

    public String retrievePlayerScoreTextView() {
        return playerScoreTextView;
    }
}
