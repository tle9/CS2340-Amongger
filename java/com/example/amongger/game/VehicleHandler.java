package com.example.amongger.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class VehicleHandler {

    private final Vehicles[] vehicleList;
    private final int level;

    private final int width;
    private final int height;

    private final Bitmap[] v;

    public VehicleHandler(Vehicles[] vehicleList, int level, int width, int height, Bitmap[] v) {
        this.vehicleList = vehicleList;
        this.level = level;
        this.width = width;
        this.height = height;
        this.v = v;
        generateVehicles();
    }

    public void generateVehicles() {
        for (int i = 0; i < vehicleList.length; i++) {
            vehicleList[i] = new Vehicles(0, 0, width, height);
            vehicleList[i].generateXOffset();
            vehicleList[i].generateYOffset(i, level);
            vehicleList[i].randomizeDirection();
        }
    }

    public void animate() {
        for (Vehicles vehicles : vehicleList) {
            vehicles.move();
        }
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < Constants.getRoadTiles(level); i++) {
            Vehicles vehicle = vehicleList[i];
            canvas.drawBitmap(Bitmap.createScaledBitmap(v[vehicle.getVehicleID()],
                    vehicle.getVehicleWidth(),
                    vehicle.getVehicleHeight(),
                    false),
                    vehicle.getX(),
                    vehicle.getY(),
                    null);
        }
    }

    public boolean checkAllCollision(PlayerSprite p) {
        for (Vehicles vehicle : vehicleList) {
            if (collision(p, vehicle)) {
                return true;
            }
        }
        return false;
    }

    private boolean collision(PlayerSprite p, Vehicles v) {
        int pX = p.getPlayerX();
        int pY = p.getPlayerY();
        int pWidth = width / 12;
        int pHeight = height / 21;
        return pY < (v.getY() + v.getVehicleHeight())
                && (pY + pHeight) > v.getY()
                //Vehicle width is multiplied by -1 if direction is right
                && pX < (v.getX() + Math.abs(v.getVehicleWidth()))
                && (pX + pWidth) > v.getX();
    }

    /**
     * For sprint4 JUnit testing
     * @param index index of vehicle
     * @param x x position
     * @param y y position
     */
    public void relocateVehicle(int index, int x, int y) {
        vehicleList[index].setX(x);
        vehicleList[index].setY(y);
    }
}
