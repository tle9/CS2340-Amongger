package com.example.amongger;

import static org.junit.Assert.*;

import com.example.amongger.game.SetDifficulty;

import org.junit.Test;

public class DifficultyTestRedo {
    @Test
    public void level1(){
        SetDifficulty example = new SetDifficulty(1);
        assertEquals(example.getDifficulty(), 1);
        assertEquals(example.getLives(), 3);
    }

    @Test
    public void level2(){
        SetDifficulty example = new SetDifficulty(2);
        assertEquals(example.getDifficulty(), 2);
        assertEquals(example.getLives(), 2);
    }

    @Test
    public void level3(){
        SetDifficulty example = new SetDifficulty(3);
        assertEquals(example.getDifficulty(), 3);
        assertEquals(example.getLives(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void InvalidLevel() {
        SetDifficulty example = new SetDifficulty(4);
    }
}