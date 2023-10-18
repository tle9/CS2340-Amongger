package com.example.amongger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.amongger.game.Constants;
import com.example.amongger.game.PlayerSprite;
import com.example.amongger.game.VehicleHandler;
import com.example.amongger.game.Vehicles;

import org.junit.Test;

public class RiverCollisionTest {
    /**
     * JUnit for Sprint4
     *
     * Tests river collisions in all three difficulties
     */
    @Test
    public void RiverCollisionDifficulty1Test(){
        PlayerSprite p = new PlayerSprite(null, -1, 240, 160, 10, 5);

        //Collision Check
        boolean isColliding = p.resetIfColliding(p.getPlayerY() < Constants.LEVEL1_RIVER_START);    //River collider start pos

        assertTrue(isColliding);

        //Exit collision range to test if not colliding
        p.spritePosAndDir('D', 5, Constants.LEVEL1_RIVER_START + 1);

        isColliding = p.resetIfColliding(p.getPlayerY() < Constants.LEVEL1_RIVER_START);
        assertFalse(isColliding);
    }

    @Test
    public void RiverCollisionDifficulty2Test(){
        PlayerSprite p = new PlayerSprite(null, -1, 240, 160, 10, 5);

        //Collision Check
        boolean isColliding = p.resetIfColliding(p.getPlayerY() < Constants.LEVEL2_RIVER_START);    //River collider start pos

        assertTrue(isColliding);

        //Exit collision range to test if not colliding
        p.spritePosAndDir('D', 5, Constants.LEVEL2_RIVER_START + 1);

        isColliding = p.resetIfColliding(p.getPlayerY() < Constants.LEVEL2_RIVER_START);
        assertFalse(isColliding);
    }

    @Test
    public void RiverCollisionDifficulty3Test(){
        PlayerSprite p = new PlayerSprite(null, -1, 240, 160, 10, 5);

        //Collision Check
        boolean isColliding = p.resetIfColliding(p.getPlayerY() < Constants.LEVEL3_RIVER_START);    //River collider start pos

        assertTrue(isColliding);

        //Exit collision range to test if not colliding
        p.spritePosAndDir('D', 5, Constants.LEVEL3_RIVER_START + 1);

        isColliding = p.resetIfColliding(p.getPlayerY() < Constants.LEVEL3_RIVER_START);
        assertFalse(isColliding);
    }

}
