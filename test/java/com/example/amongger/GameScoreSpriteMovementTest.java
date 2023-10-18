package com.example.amongger;

import static org.junit.Assert.*;

//import com.example.amongger.GameActivity;

import com.example.amongger.game.GameScoreSpriteMovement;

import org.junit.Test;

/**
 * Sprint3 Junit tests.
 */
public class GameScoreSpriteMovementTest {
    @Test
    public void spriteMoveUpScore() {
        GameScoreSpriteMovement a = new GameScoreSpriteMovement();
        System.out.println("Press: " + a.movementPress("W") + ", move: " + a.scoreUpdate());
        assertEquals(true, a.scoreUpdate());
    }

    @Test
    public void spriteMoveDownScore() {
        GameScoreSpriteMovement a = new GameScoreSpriteMovement();
        System.out.println("Press: " + a.movementPress("S") + ", move: " + a.scoreUpdate());
        assertEquals(false, a.scoreUpdate());
    }

    @Test
    public void spriteMoveLeftScore() {
        GameScoreSpriteMovement a = new GameScoreSpriteMovement();
        System.out.println("Press: " + a.movementPress("A") + ", move: " + a.scoreUpdate());
        assertEquals(false, a.scoreUpdate());
    }

    @Test
    public void spriteMoveRightScore() {
        GameScoreSpriteMovement a = new GameScoreSpriteMovement();
        System.out.println("Press: " + a.movementPress("D") + ", move: " + a.scoreUpdate());
        assertEquals(false, a.scoreUpdate());
    }

    @Test
    public void spriteNotMove() {
        GameScoreSpriteMovement a = new GameScoreSpriteMovement();
        assertThrows(IllegalArgumentException.class, () -> {
            a.movementPress("P");
        });
        System.out.println("Exception expected: To move must press W, A, S, or D");
    }
}