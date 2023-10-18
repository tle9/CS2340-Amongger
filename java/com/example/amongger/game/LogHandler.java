package com.example.amongger.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class LogHandler {

    private final Logs[] logList;
    private final int level;

    private final int width;
    private final int height;
    private int logVelocity;
    private int logWidth;
    private final Bitmap[] l;

    public LogHandler(Logs[] logList, int level, int width, int height, Bitmap[] l) {
        this.logList = logList;
        this.level = level;
        this.width = width;
        this.height = height;
        this.logVelocity = 0;
        this.logWidth = 0;
        this.l = l;
        generateLogs();
    }

    public void generateLogs() {
        for (int i = 0; i < logList.length; i++) {
            logList[i] = new Logs(0, 0, width, height);
            logList[i].generateXOffset();
            logList[i].generateYOffset(i, level);
            logList[i].randomizeDirection();
        }
    }

    public void animate() {
        for (Logs logs : logList) {
            logs.move();
        }
    }

    public void draw(Canvas canvas) {
        int start = Constants.getRiverTiles(level);
        for (int i = 0; i < logList.length; i++) {
            Logs log = logList[i];
            canvas.drawBitmap(Bitmap.createScaledBitmap(l[log.getLogID()],
                            log.getLogWidth(),
                            log.getLogHeight(),
                            false),
                    log.getX(),
                    log.getY(),
                    null);
        }
    }

    public boolean checkAllCollision(PlayerSprite p) {
        for (Logs log : logList) {
            if (collision(p, log)) {
                logVelocity = log.getLogVelocity();
                logWidth = log.getLogWidth();
                return true;
            }
        }
        return false;
    }

    private boolean collision(PlayerSprite p, Logs l) {
        int pX = p.getPlayerX();
        int pY = p.getPlayerY();
        int pWidth = width / 12;
        int pHeight = height / 21;
        return (pY < (l.getY() + l.getLogHeight())
                && ((pY + pHeight) > l.getY())) &&
                //Log width is multiplied by -1 if direction is right
            (pX < (l.getX() + Math.abs(l.getLogWidth()))
                && (pX + pWidth) > l.getX());
    }
    /**
     * For sprint4 JUnit testing
     * @param index index of log
     * @param x x position
     * @param y y position
     */
    public void relocateLog(int index, int x, int y) {
        logList[index].setX(x);
        logList[index].setY(y);
    }
    public int getCurrentLogVelocity() {
        return logVelocity;
    }
    public int getCurrentLogWidth() {
        return logWidth;
    }
}
