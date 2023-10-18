package com.example.amongger.game;

import com.example.amongger.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Logs {
    //Logs
    private int[] log = new int[] {R.drawable.satelite, R.drawable.goldsatelite};
    private int logID;

    private int screenWidth;
    private int screenHeight;

    private int x;
    private int y;

    private int logWidth;
    private int logHeight;

    private int velocity;

    public Logs(int x, int y, int width, int height) {
        this.logID = generateRandomSeed(1.9);
        this.x = x;
        this.y = y;
        this.screenWidth = width;
        this.screenHeight = height;

        //SPEED
        switch (logID) {
        case 0:
            velocity = Constants.SPEED_SAT;
            logWidth = screenWidth / 6;
            break;
        case 1:
            velocity = Constants.SPEED_SATG;
            logWidth = screenWidth / 12;
            break;
        default:
            break;
        }
        logHeight = screenHeight / 21;
    }

    public void randomizeDirection() {
        int rand = generateRandomSeed(10);
        if (rand % 2 == 0) {
            velocity *= -1;
            logWidth *= -1;
        }
    }

    public void generateXOffset() {
        this.x = (int) Math.round(Math.random() * screenWidth);
    }

    public void generateYOffset(int index, int level) {
        int tileHeight = screenHeight / 21;
        int topRow = Constants.getRiverRow(level);
        this.y = ((topRow - index) * tileHeight);
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
                    x = -logWidth;
                }
                x += velocity;
            }
        };
        exe.scheduleAtFixedRate(run, 10, 24, TimeUnit.MILLISECONDS);
    }

    public int getLogID() {
        return logID;
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

    public int getLogWidth() {
        return logWidth;
    }

    public int getLogHeight() {
        return logHeight;
    }
    public int getLogVelocity() {return velocity; }


}
