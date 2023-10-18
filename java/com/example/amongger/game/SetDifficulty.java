package com.example.amongger.game;


public class SetDifficulty {
    private int difficulty;
    private int lives;

    /**
     * SetDifficulty constructor
     * @param difficulty the level of difficulty
     */
    public SetDifficulty(int difficulty) {
        if (difficulty < 1 || difficulty > 3) {
            throw new IllegalArgumentException("Invalid difficulty");
        }
        this.difficulty = difficulty;
        this.lives = 4 - difficulty;
    }

    /**
     * Getter difficulty
     * @return difficulty
     */
    public int getDifficulty() {
        return this.difficulty;
    }

    /**
     * Getter lives
     * @return lives
     */
    public int getLives() {
        return this.lives;
    } 
    
}
