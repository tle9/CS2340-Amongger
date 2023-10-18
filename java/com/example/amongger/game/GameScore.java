package com.example.amongger.game;
//import java.util.*;
//import com.example.amongger.map.MapLayout;
public class GameScore {
    private int score;
    private static final int TOTAL_ROW = 19;
    private int difficultyLevel;
    private int[] rowIdentifier;

    /**
     * Constructor.
     */
    public GameScore() {
        score = 0;
        rowIdentifier = new int[TOTAL_ROW];
    }

    /**
     * GameScore Constructor.
     * @param difficultyLevel level of difficulty.
     */
    public GameScore(int difficultyLevel) {
        this();
        this.difficultyLevel = difficultyLevel;
    }
    /**
     *  Assigns each row with randomized identifier [0-2] except the safe tiles
     */
    public void randomizeArray() {
        // Only assigns identifiers to the rows except safe tiles
        for (int i = 2; i < rowIdentifier.length; i++) {
            double rand = Math.random();
            rowIdentifier[i] = (int) (rand * 3) + 1;
        }
        // Based on the difficulty, assign 0 on safe tiles
        switch (difficultyLevel) {
        case 0:
            rowIdentifier[2] = 0;
            rowIdentifier[9] = 0;
            rowIdentifier[10] = 0;
            break;
        case 1:
            rowIdentifier[2] = 0;
            rowIdentifier[7] = 0;
            break;
        case 2:
            rowIdentifier[13] = 0;
            break;
        default:
        }
        rowIdentifier[18] = 10;
    }

    /**
     * Getter for row identifier.
     * @return rowIndentifier.
     */
    public int[] getRowIdentifier() {
        return rowIdentifier;
    }

}
