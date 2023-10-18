package com.example.amongger;

import static org.junit.Assert.assertEquals;

import com.example.amongger.game.Constants;
import com.example.amongger.game.PlayerSprite;
import com.example.amongger.game.VehicleHandler;
import com.example.amongger.game.Vehicles;

import org.junit.Test;

public class VehicleCollisionTest {
    /**
     * SPRINT 4 JUnits
     *
     * Tests Whether or not collisions are registered for vehicles
     */
    @Test
    public void VehicleCollision(){
        PlayerSprite p = new PlayerSprite(null, -1, 240, 160, 10, 5);

        int screenWidth = 240;
        int screenHeight = 160;

        VehicleHandler vHandler = new VehicleHandler(new Vehicles[Constants.getRoadTiles(0)], 0, screenWidth, screenHeight, null);
        vHandler.generateVehicles();

        vHandler.relocateVehicle(0, p.getPlayerX() * (screenWidth / 12), p.getPlayerY() * (screenHeight / 21));
        boolean collisionCheck = vHandler.checkAllCollision(p);


        assertEquals(true, collisionCheck);
    }

    @Test
    public void NoVehicleCollision(){
        PlayerSprite p = new PlayerSprite(null, -1, 240, 160, 10, 5);

        int screenWidth = 240;
        int screenHeight = 160;

        VehicleHandler vHandler = new VehicleHandler(new Vehicles[Constants.getRoadTiles(0)], 0, screenWidth, screenHeight, null);
        vHandler.generateVehicles();

        vHandler.relocateVehicle(0, (p.getPlayerX() + 10) * (screenWidth / 12), (p.getPlayerY() + 10) * (screenHeight / 21));
        boolean collisionCheck = vHandler.checkAllCollision(p);

        assertEquals(false, collisionCheck);
    }

}
