package com.example.amongger.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;

import com.example.amongger.R;

public class PlayerSprite {
    private Bitmap spriteUp;
    private Bitmap spriteLeft;
    private Bitmap spriteRight;
    private Bitmap spriteDown;
    private Bitmap spriteDir;
    private final int width;
    private final int height;
    private int playerX;
    private int playerY;
    public PlayerSprite(Context c, int selectedColor, int dispWidth, int dispHeight,
                        int posX, int posY) {
        if (selectedColor == 0) {
            setSusYellow(c);
        } else if (selectedColor == 1) {
            setSusRed(c);
        } else if (selectedColor == 2) {
            setSusGreen(c);
        }
        this.spriteDir = spriteUp;
        this.width = dispWidth;
        this.height = dispHeight;
        this.playerX = posX;
        this.playerY = posY;
    }
    private void setSusYellow(Context c) {
        spriteUp = BitmapFactory.decodeResource(c.getResources(), R.drawable.yellow_up);
        spriteLeft = BitmapFactory.decodeResource(c.getResources(), R.drawable.yellow_left);
        spriteRight = BitmapFactory.decodeResource(c.getResources(), R.drawable.yellow_right);
        spriteDown = BitmapFactory.decodeResource(c.getResources(), R.drawable.yellow_down);
    }
    private void setSusRed(Context c) {
        spriteUp = BitmapFactory.decodeResource(c.getResources(), R.drawable.red_up);
        spriteLeft = BitmapFactory.decodeResource(c.getResources(), R.drawable.red_left);
        spriteRight = BitmapFactory.decodeResource(c.getResources(), R.drawable.red_right);
        spriteDown = BitmapFactory.decodeResource(c.getResources(), R.drawable.red_down);
    }
    private void setSusGreen(Context c) {
        spriteUp = BitmapFactory.decodeResource(c.getResources(), R.drawable.green_up);
        spriteLeft = BitmapFactory.decodeResource(c.getResources(), R.drawable.green_left);
        spriteRight = BitmapFactory.decodeResource(c.getResources(), R.drawable.green_right);
        spriteDown = BitmapFactory.decodeResource(c.getResources(), R.drawable.green_down);
    }
    public Bitmap spritePosAndDir(char movement, int posX, int posY) {
        switch (movement) {
        case 'L':
            spriteDir = Bitmap.createScaledBitmap(spriteLeft, width / 12,
                height / 21, false);
            break;
        case 'R':
            spriteDir = Bitmap.createScaledBitmap(spriteRight, width / 12,
                height / 21, false);
            break;
        case 'D':
            spriteDir = Bitmap.createScaledBitmap(spriteDown, width / 12,
                height / 21, false);
            break;
        default:
            spriteDir = Bitmap.createScaledBitmap(spriteUp, width / 12,
                height / 21, false);
            break;
        }
        playerX = posX;
        playerY = posY;
        return spriteDir;
    }
    public int getPlayerX() {
        return playerX;
    }
    public int getPlayerY() {
        return playerY;
    }

    /**
     * Scuffed JUnit helper method for Sprint4
     * @param isColliding if colliding
     * @return reset position
     */
    public boolean resetIfColliding(boolean isColliding) {
        if (isColliding) {
            playerX = 5;
            playerY = 20;
            spriteDir = spriteUp;
            return true;
        }
        return false;
    }
}
