package com.example.amongger;


public class PlayerName {
    private String name;

    /**
     * Constructor test player name
     * @param name player name
     */
    public PlayerName(String name) {
        this.name = name;
    }

    /**
     * Setter player name
     * @return the player name
     * @throws IllegalArgumentException if play is empty, null, or whitespace detected
     */
    public String getName() throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Player name is null!");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Player name field is empty!");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name field detect white space!");
        } else {
            return name;
        }
    }

    /**
     * Setter player name
     * @param name player name
     */
    public void setName(String name) {
        this.name = name;
    }
}