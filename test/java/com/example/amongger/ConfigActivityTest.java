package com.example.amongger;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigActivityTest {

    @Test
    public void testNameString() {
        PlayerName p = new PlayerName("Abcdefg hijklm 012345678 9 !");
        assertEquals("Abcdefg hijklm 012345678 9 !", p.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameNull() {
        PlayerName p = new PlayerName(null);
        p.getName();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameEmpty() {
        PlayerName p = new PlayerName("");
        p.getName();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameWhiteSpace() {
        PlayerName p = new PlayerName("          ");
        p.getName();
    }
}
