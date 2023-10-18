package com.example.amongger.game;

import com.example.amongger.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Vehicles {
    //Vehicles
    private int[] vehicle = new int[] {R.drawable.airship, R.drawable.ufo, R.drawable.rocket};
    private int vehicleID;

    private final int screenWidth;
    private final int screenHeight;

    private int x;
    private int y;

    private int vehicleWidth;
    private int vehicleHeight;

    private int velocity;

    public Vehicles(int x, int y, int width, int height) {
        this.vehicleID = generateRandomSeed(2.9);
        this.x = x;
        this.y = y;
        this.screenWidth = width;
        this.screenHeight = height;

        //SPEED
        switch (vehicleID) {
        case 0:
            velocity = Constants.SPEED_AIRSHIP;
            vehicleWidth = screenWidth / 6;
            break;
        case 1:
            velocity = Constants.SPEED_UFO;
            vehicleWidth = screenWidth / 12;
            break;
        case 2:
            velocity = Constants.SPEED_ROCKET;
            vehicleWidth = screenWidth / 9;
            break;
        default:
            break;
        }
        vehicleHeight = screenHeight / 21;
    }

    public void randomizeDirection() {
        int rand = generateRandomSeed(10);
        if (rand % 2 == 0) {
            velocity *= -1;
            vehicleWidth *= -1;
        }
    }

    public void generateXOffset() {
        this.x = (int) Math.round(Math.random() * screenWidth);
    }

    public void generateYOffset(int index, int level) {
        int tileHeight = screenHeight / 21;
        int topRow = 18;
        if (level == 2) {   //Hmmm...
            topRow = 20;
            index++;
        }
        this.y = (topRow - index) * tileHeight;
    }

    private int generateRandomSeed(double range) {
        return (int) Math.floor(Math.random() * range);
    }

    public void move() {
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(2);
        Runnable run = new Runnable() {
            @Override
            public void run() {
                if (velocity < 0 && x <= -screenWidth) {
                    x = screenWidth;
                } else if (velocity > 0 && x >= screenWidth) {
                    x = -vehicleWidth;
                }
                x += velocity;
            }
        };
        exe.scheduleAtFixedRate(run, 10, 24, TimeUnit.MILLISECONDS);
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getVehicleWidth() {
        return vehicleWidth;
    }

    public void setVehicleWidth(int vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
    }

    public int getVehicleHeight() {
        return vehicleHeight;
    }

    public void setVehicleHeight(int vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
    }

}
