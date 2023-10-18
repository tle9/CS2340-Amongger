package com.example.amongger.game;

public class GameScoreAndRoad {
    private final String start = "Start";
    private final String road = "Road";
    private final String safe = "Safe";
    private final String river = "River";
    private final String goal = "Goal";
    private int score;

    /**
     * For Junit test for the correct amount of points are given for playable tiles.
     */
    public GameScoreAndRoad() {
        this.score = 0;
    }

    /**
     * Check when sprite at which tile, start and safe tiles will not increase score.
     * @param tile the tiles.
     * @return name of the tile.
     */
    public String atTile(String tile) {
        if (tile.equals("Start")) {
            this.score = 0;
            return start;
        } else if (tile.equals("Road")) {
            this.score = 5;
            return road;
        } else if (tile.equals("Safe")) {
            this.score = 0;
            return safe;
        } else if (tile.equals("River")) {
            this.score = 10;
            return river;
        } else if (tile.equals("Goal")) {
            this.score = 30;
            return goal;
        }
        return null;
    }

    /**
     * Helper method that see if score update based on which tile.
     * @return message.
     */
    public String totalScore() {
        if (this.score != 0) {
            return "The score have updated!";
        } else {
            return "You are in start/safe tile, no score updated!";
        }
    }

    /**
     * Helper method that see if score update based on which tile.
     * @return true if tile is not start or safe.
     */
    public boolean scoreUpdate() {
        return this.score != 0;
    }
}
