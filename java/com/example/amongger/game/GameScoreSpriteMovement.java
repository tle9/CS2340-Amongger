package com.example.amongger.game;

public class GameScoreSpriteMovement {
    private final char up = 'W';
    private final char down = 'S';
    private final char left = 'A';
    private final char right = 'D';
    private int score;

    /**
     * For junit test for to keep track
     * the layer score will not increase when moving sideways or backwards.
     * Only increase when move forwards.
     */
    public GameScoreSpriteMovement() {
        this.score = 0;
    }

    /**
     * Update score when sprite move forwards.
     * @param c key
     * @return character of the key press
     */
    public char movementPress(String c) {
        if (c.equals("W")) {
            this.score = 5;
            return up;
        } else if (c.equals("S")) {
            this.score = 0;
            return this.down;
        } else if (c.equals("A")) {
            this.score = 0;
            return left;
        } else if (c.equals("D")) {
            this.score = 0;
            return right;
        } else {
            throw new IllegalArgumentException("To move must press W, A, S, or D!");
        }
    }

    /**
     * Update score when the sprite move forwards
     * @return true if sprite move forward, and false for the rest.
     */
    public boolean scoreUpdate() {
        return score != 0;
    }

    /**
     * check score
     * @return score
     */
    public int scoreCheck() {
        return this.score;
    }
}
