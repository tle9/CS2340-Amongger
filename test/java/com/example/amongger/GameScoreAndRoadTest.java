package com.example.amongger;

import static org.junit.Assert.*;

import com.example.amongger.game.GameScoreAndRoad;

import org.junit.Test;
/**
 * Sprint3 Junit tests.
 */
public class GameScoreAndRoadTest {
    @Test
    public void atStart() {
        GameScoreAndRoad a = new GameScoreAndRoad();
        System.out.println("At: " + a.atTile("Start") + " tile --> " + a.totalScore());
        assertEquals(false, a.scoreUpdate());
    }

    @Test
    public void atRoad() {
        GameScoreAndRoad a = new GameScoreAndRoad();
        System.out.println("At: " + a.atTile("Road") + " tile --> " + a.totalScore());
        assertEquals(true, a.scoreUpdate());
    }

    @Test
    public void atSafe() {
        GameScoreAndRoad a = new GameScoreAndRoad();
        System.out.println("At: " + a.atTile("Safe") + " tile --> " + a.totalScore());
        assertEquals(false, a.scoreUpdate());
    }

    @Test
    public void atRiver() {
        GameScoreAndRoad a = new GameScoreAndRoad();
        System.out.println("At: " + a.atTile("River") + " tile --> " + a.totalScore());
        assertEquals(true, a.scoreUpdate());
    }

    @Test
    public void atGoal() {
        GameScoreAndRoad a = new GameScoreAndRoad();
        System.out.println("At: " + a.atTile("Goal") + " tile --> " + a.totalScore());
        assertEquals(true, a.scoreUpdate());
    }
}