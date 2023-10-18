package com.example.amongger;

import static org.junit.Assert.assertEquals;

import com.example.amongger.game.Constants;
import com.example.amongger.game.PlayerSprite;
import com.example.amongger.game.Score;
import com.example.amongger.game.VehicleHandler;
import com.example.amongger.game.Vehicles;
import org.junit.Test;

/**
 * Sprint 4 Junit tests
 */
public class ScoreResetOnCollisionTest {
    @Test
    public void scoreResetOnVehicleCollision() {
        PlayerSprite p = new PlayerSprite(null, -1, 240, 160, 10, 5);

        //Dimensions
        int screenWidth = 240;
        int screenHeight = 160;

        VehicleHandler vHandler = new VehicleHandler(new Vehicles[Constants.getRoadTiles(0)], 0, screenWidth, screenHeight, null);
        vHandler.generateVehicles();

        //Move vehicle to player to check collision
        vHandler.relocateVehicle(0, p.getPlayerX() * (screenWidth / 12), p.getPlayerY() * (screenHeight / 21));
        boolean collisionCheck = vHandler.checkAllCollision(p);

        Score scoreSystem = new Score("Difficulty: Level 1", null);
        int score = 2000;
        if (collisionCheck) {
            scoreSystem.scoreResetTest();
            score = scoreSystem.getScoreTest();
        }
        assertEquals(0, score);
    }

    @Test
    public void scoreResetOnRiverCollision() {
        PlayerSprite p = new PlayerSprite(null, -1, 240, 160, 10, 5);

        //Dimensions
        int screenWidth = 240;
        int screenHeight = 160;

        VehicleHandler vHandler = new VehicleHandler(new Vehicles[Constants.getRoadTiles(0)], 0, screenWidth, screenHeight, null);
        vHandler.generateVehicles();

        //Move player to river
        p.spritePosAndDir('U', 5, Constants.LEVEL1_RIVER_START - 1);

        //Move vehicle to player to check collision
        boolean collisionCheck = p.getPlayerY() < Constants.LEVEL1_RIVER_START;

        Score scoreSystem = new Score("Difficulty: Level 1", null);
        int score = 2000;
        if (collisionCheck) {
            scoreSystem.scoreResetTest();
            score = scoreSystem.getScoreTest();
        }
        assertEquals(0, score);
    }
}
