package com.example.amongger;

import static org.junit.Assert.assertEquals;

import android.app.Instrumentation;

import com.example.amongger.game.GameScore;
import com.example.amongger.game.GameScoreSpriteMovement;


import org.junit.Test;


/**
 * Sprint 3 junit
 */
public class ScoreIncrementTest {

    @Test
    public void LeftMovement(){
        GameScoreSpriteMovement sprite = new GameScoreSpriteMovement();
        sprite.movementPress("A");
        assertEquals(sprite.scoreCheck(), 0);
    }

    @Test
    public void RightMovement(){
        GameScoreSpriteMovement sprite = new GameScoreSpriteMovement();
        sprite.movementPress("D");
        assertEquals(sprite.scoreCheck(), 0);

    }

    @Test
    public void DownMovement(){
        GameScoreSpriteMovement sprite = new GameScoreSpriteMovement();
        sprite.movementPress("S");
        assertEquals(sprite.scoreCheck(), 0);

    }

    @Test
    public void UpMovement(){
        GameScoreSpriteMovement sprite = new GameScoreSpriteMovement();
        sprite.movementPress("W");
        assertEquals(sprite.scoreCheck(), 5);
    }

}
