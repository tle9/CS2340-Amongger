package com.example.amongger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.example.amongger.game.GameScore;
import com.example.amongger.game.GameScoreSpriteMovement;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Sprint3 Junit tests.
 */
public class GameScoreTest extends TestCase {

//    @Test
//    public void testRandomizer() {
//        GameScore score = new GameScore();
//        int[] rows = score.getRowIdentifier();
//
//        // Test empty array
//        assertEquals(rows.length, 18);
//        int zeroSum = 0;
//        for (int row : rows) {
//            zeroSum += row;
//        }
//        assertEquals(zeroSum, 0);
//
//        // Test randomized array
//        score.randomizeArray();
//        for (int row : rows) {
//            zeroSum += row;
//            System.out.println(row);
//        }
//        assertTrue(zeroSum != 0);
//    }
//
//    @Test
//    public void testInitialScore() {
//        assertEquals("Score: 0", GameActivity.testIntialScore(0));
//    }
//
//    @Test
//    public void testInitialScoreNotZero() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            GameActivity.testIntialScore(100);
//        });
//    }
}