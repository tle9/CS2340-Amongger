package com.example.amongger;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.amongger.game.Constants;
import com.example.amongger.game.GameScoreSpriteMovement;
import com.example.amongger.game.PlayerSprite;
import com.example.amongger.game.VehicleHandler;
import com.example.amongger.game.Vehicles;

import org.junit.Test;
import org.junit.runner.manipulation.Ordering;

public class PlayerRespawnTest {
    /**
     * SPRINT 4 JUnits
     *
     * Tests respawn position after a collision
     */
    @Test
    public void RespawnOnVehicleCollision(){
        PlayerSprite p = new PlayerSprite(null, -1, 240, 160, 10, 5);

        //Dimensions
        int screenWidth = 240;
        int screenHeight = 160;

        VehicleHandler vHandler = new VehicleHandler(new Vehicles[Constants.getRoadTiles(0)], 0, screenWidth, screenHeight, null);
        vHandler.generateVehicles();

        //Move vehicle to player to check collision
        vHandler.relocateVehicle(0, p.getPlayerX() * (screenWidth / 12), p.getPlayerY() * (screenHeight / 21));
        boolean collisionCheck = vHandler.checkAllCollision(p);

        //Player relocation handler
        p.resetIfColliding(collisionCheck);

        assertEquals(5, p.getPlayerX());
        assertEquals(20, p.getPlayerY());
    }

    @Test
    public void RespawnOnRiverCollision(){
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

        //Player relocation handler
        p.resetIfColliding(collisionCheck);

        assertEquals(5, p.getPlayerX());
        assertEquals(20, p.getPlayerY());
    }
}
