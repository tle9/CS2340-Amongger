package com.example.amongger.game;

public class Constants {

    //ROAD
    public static final int LEVEL1_ROAD_TILES = 6;
    public static final int LEVEL2_ROAD_TILES = 4;
    public static final int LEVEL3_ROAD_TILES = 11;

    //RIVER
    public static final int LEVEL1_RIVER_START = 10; //"7 logs"
    public static final int LEVEL2_RIVER_START = 13; //"4 logs"
    public static final int LEVEL3_RIVER_START = 7;  //"10 logs"
    public static final int RIVER_END = 4; // River always ends at R4
    //VEHICLES
    public static final int SPEED_UFO = 10;
    public static final int SPEED_ROCKET = 14;
    public static final int SPEED_AIRSHIP = 8;

    public static final int SPEED_SAT = 10;

    public static final int SPEED_SATG = 5;

    public static int getLevelID(String level) {
        switch (level) {
        case "Difficulty: Level 1":
            return 0;
        case "Difficulty: Level 2":
            return 1;
        case "Difficulty: Hmmm":
            return 2;
        default:
            throw new java.lang.IllegalArgumentException("[Parameter Error]: Vehicle ID "
                    + "is out of range (0-2)");
        }
    }

    public static int getRoadTiles(String level) {
        switch (level) {
        case "Difficulty: Level 1":
            return LEVEL1_ROAD_TILES;
        case "Difficulty: Level 2":
            return LEVEL2_ROAD_TILES;
        case "Difficulty: Hmmm":
            return LEVEL3_ROAD_TILES;
        default:
            throw new java.lang.IllegalArgumentException("[Parameter Error]: "
                    + "Vehicle ID is out of range (0-2)");
        }
    }

    public static int getRoadTiles(int level) {
        switch (level) {
        case 0:
            return LEVEL1_ROAD_TILES;
        case 1:
            return LEVEL2_ROAD_TILES;
        case 2:
            return LEVEL3_ROAD_TILES;
        default:
            throw new java.lang.IllegalArgumentException("[Parameter Error]: Vehicle ID "
                    + "is out of range (0-2)");
        }
    }
    public static int getRiverTiles(String level) {
        switch (level) {
            case "Difficulty: Level 1":
                return 7;
            case "Difficulty: Level 2":
                return 10;
            case "Difficulty: Hmmm":
                return 4;
            default:
                throw new java.lang.IllegalArgumentException("[Parameter Error]: "
                        + "Log ID is out of range (0-2)");
        }
    }
    public static int getRiverTiles(int level) {
        switch (level) {
            case 0:
                return 7;
            case 1:
                return 10;
            case 2:
                return 4;
            default:
                throw new java.lang.IllegalArgumentException("Level out of range (0-2)");
        }
    }

    public static int getRiverRow(int level) {
        switch (level) {
            case 0:
                return 10;
            case 1:
                return 13;
            case 2:
                return 7;
            default:
                throw new java.lang.IllegalArgumentException("Level out of range (0-2)");
        }
    }
}
