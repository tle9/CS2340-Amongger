package com.example.amongger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.amongger.game.Constants;
import com.example.amongger.game.Map;
import com.example.amongger.game.MapLayoutManager;
import com.example.amongger.game.PlayerSprite;
import com.example.amongger.game.Score;
import com.example.amongger.game.VehicleHandler;
import com.example.amongger.game.Vehicles;
import com.example.amongger.game.LogHandler;
import com.example.amongger.game.Logs;

public class GameSurface extends SurfaceView implements Runnable {
    //SurfaceView variables needed to make work.
    private Thread uiThread;
    private boolean threadCheck;
    private SurfaceHolder sHolder;
    private Context context;

    //Display metrics for resizing
    private int width;
    private int height;

    //Level difficulty to determine map.
    private String level;

    private int riverStartTile;

    //Tiles used for tilemapping. 12 x 21 grid.
    private int[] tiles = {R.drawable.goal1, R.drawable.goal3, R.drawable.goal4,
        R.drawable.button_tile, R.drawable.goal5, R.drawable.goal2, R.drawable.river,
        R.drawable.road2, R.drawable.start1, R.drawable.start2, R.drawable.start3};
    //Tilemap layout [col][row]
    private MapLayoutManager mapManager;
    private int[][] mapLayout;
    private Map map;

    //Player Sprite
    private PlayerSprite playerSprite;
    private Bitmap sprite;

    //Player Position Coords x = [col] / y = [row]
    private int pX = 5; //Initial x.
    private int pY = 20; //Initial y.
    private int translateX;
    private int translateY;
    //Player movement indicator.
    private char playerMovement = 'U'; //Initial directional view.

    //Player lives
    private int lives;
    private TextView livesText;
    //Score System
    private Score scoreHandler;
    private int score;
    private String scoreOutput;
    private int visited;
    //Vehicles
    private Bitmap[] vBitmap = {BitmapFactory.decodeResource(getResources(), R.drawable.airship),
        BitmapFactory.decodeResource(getResources(), R.drawable.ufo),
        BitmapFactory.decodeResource(getResources(), R.drawable.rocket)};

    private VehicleHandler vHandler;
    //Satellites
    private Bitmap[] lBitmap = {BitmapFactory.decodeResource(getResources(), R.drawable.satelite),
        BitmapFactory.decodeResource(getResources(), R.drawable.goldsatelite),};
    private LogHandler lHandler;
    private boolean collided;
    public GameSurface(Context context) {
        super(context);
    }

    public GameSurface(Context c, int w, int h, String lvl, int s, Score sHandler,
                       TextView livesView) {
        this(c);
        this.context = c;
        this.sHolder = getHolder();
        this.width = w;
        this.height = h;
        this.level = lvl;
        if (level.equals("Difficulty: Level 1")) {
            this.lives = 10;
        } else if (level.equals("Difficulty: Level 2")) {
            this.lives = 6;
        } else {
            this.lives = 2;
        }
        this.livesText = livesView;
        this.map = new Map(c, w, h, level);
        this.mapManager = new MapLayoutManager(lvl);
        this.mapLayout = mapManager.getMapLayout();
        this.playerSprite = new PlayerSprite(c, s, width, height, pX, pY);
        this.translateX = pX * (width / 12);
        this.translateY = pY * (height /21);
        this.vHandler = new VehicleHandler(new Vehicles[Constants.getRoadTiles(level)],
            Constants.getLevelID(level),
            width,
            height,
            vBitmap);
        this.lHandler = new LogHandler(new Logs[Constants.getRiverTiles(level)],
            Constants.getLevelID(level),
            width,
            height,
            lBitmap);
        this.collided = false;
        this.scoreHandler = sHandler;
        this.score = 0;
        this.visited = 20;
        this.riverStartTile = riverStart();
        this.collided = false;
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        vHandler.animate();
        lHandler.animate();
    }
    @Override
    public void run() {
        while (threadCheck) {
            onVehicleCollision();
            updatePlayerPosition(playerMovement);
            onRiverCollision();
            draw();
        }
    }

    public void pause() {
        threadCheck = false;
        try {
            uiThread.join();
        } catch (InterruptedException e) {
            //Shrug
        }
    }

    public void resume() {
        threadCheck = true;
        uiThread = new Thread(this);
        uiThread.start();
    }

    private int riverStart() {
        switch (Constants.getLevelID(level)) {
        case 0:
            return Constants.LEVEL1_RIVER_START;
        case 1:
            return Constants.LEVEL2_RIVER_START;
        case 2:
            return Constants.LEVEL3_RIVER_START;
        default:
            return 0;
        }
    }

    /**
     * Generates basically everything except for the TextViews.
     * This will continuously draw the map along with any updates made
     * until the surface is destroyed by closing the game or surfaceDestroyed().
     * have not made a surfaceDestroyed() since I didn't think it was necessary at the current.
     *
     * Note:
     * Everything is drawn in a top-down hierarchy.
     */
    protected void draw() {
        if (sHolder.getSurface().isValid()) {
            Canvas canvas = sHolder.lockCanvas();
            //Chaotically draws map based on the tileLayout loaded.
            for (int col = 0; col < mapLayout.length; col++) {
                for (int row = 0; row < mapLayout[0].length; row++) {
                    if (mapLayout[col][row] != 69) {
                        canvas.drawBitmap(map.mapDraw(col, row),row * (width / 12),
                            col * (height / 21), null);
                    }
                }
            }
            //Draw Road Obstacles here
            //Moves all vehicles (backend)
            vHandler.draw(canvas);      //Draws current vehicle list\
            lHandler.draw(canvas);
            //Draw sprite here
            canvas.drawBitmap(sprite, translateX, translateY,null);
            sHolder.unlockCanvasAndPost(canvas);
        }
    }
    public void updatePlayerPosition(char posInput) {
        playerMovement = posInput;
        sprite = playerSprite.spritePosAndDir(playerMovement, translateX, translateY);
    }

    public void resetPlayerPosition() {
        pX = 5;
        translateX = pX * (width / 12);
        pY = 20;
        translateY = pY * (height / 21);
        visited = 20;
        updatePlayerPosition('U');
    }

    public void movePlayer() {
        if (playerMovement == 'U') {
            if (pY * (height / 21) != 3 * (height / 21)) {
                if (pY - 1 < visited) {
                    score = scoreHandler.getRowPoint(pY);
                    visited--;
                }
                pY--;
                if (pY == 3) {
                    pX = translateX / (width / 12);
                    translateX = pX * (width / 12);
                    if (checkGoalColumns()) {
                        score = scoreHandler.getRowPoint(pY);
                        initWin();
                    }
                }
                translateY = pY * (height / 21);
            }
        } else if (playerMovement == 'D') {
            if (pY * (height / 21) != 20 * (height / 21)) {
                pY++;
                translateY = pY * (height / 21);
            }
        } else if (playerMovement == 'L') {
            if (pX * (width / 12) != 0) {
                if (collided) {
                    if (lHandler.getCurrentLogVelocity() > 0) {
                        translateX -= (width / 12) / lHandler.getCurrentLogVelocity() * 2;
                    } else {
                        translateX += (width / 12) / lHandler.getCurrentLogVelocity() * 2;
                    }
                } else {
                    pX--;
                    translateX = pX * (width / 12);
                }
            }
        } else if (playerMovement == 'R') {
            if (pX * (width / 12) != 11 * (width / 12)) {
                if (collided) {
                    if (lHandler.getCurrentLogVelocity() > 0) {
                        translateX += (width / 12) / lHandler.getCurrentLogVelocity() * 2;
                    } else {
                        translateX -= (width / 12) / lHandler.getCurrentLogVelocity() * 2;
                    }
                } else {
                    pX++;
                    translateX = pX * (width / 12);
                }
            }
        }
    }

    private boolean checkGoalColumns() {
        return pX == 1 || pX == 4 || pX == 7 || pX == 10;
    }

    public void onVehicleCollision() {
        if (vHandler.checkAllCollision(playerSprite)) {
            lives--;
            playerLifeUpdate();
            resetPlayerPosition();
            scoreHandler.resetScore();
        }
    }

    public void onRiverCollision() {
        if (pY <= riverStartTile && pY >= Constants.RIVER_END) {
            if (lHandler.checkAllCollision(playerSprite)) {
                collided = true;
                translateX += lHandler.getCurrentLogVelocity() * 2;
                if (translateX <= 0 || translateX >= 12 * (width / 12)) {
                    scoreHandler.resetScore();
                    collided = false;
                    lives--;
                    playerLifeUpdate();
                    resetPlayerPosition();
                }
            } else {
                    scoreHandler.resetScore();
                    collided = false;
                    lives--;
                    playerLifeUpdate();
                    resetPlayerPosition();
            }
        }
    }

    public int getScore() {
        scoreOutput = "Score: " + score;
        return score;
    }
    public void playerLifeUpdate() {
        String livesOutput = "Lives: " + lives;
        if (lives != 0) {
            livesText.post(new Runnable() {
                @Override
                public void run() {
                    livesText.setText(livesOutput);
                }
            });
        } else {
            initGameOver();
            //initWin(); //for testing win screen
        }
    }
    private void initGameOver() {
        Intent intent = new Intent(context, GameOverActivity.class);
        scoreOutput = "Final Score: " + score;
        intent.putExtra("score", scoreOutput);
        context.startActivity(intent);
        ((Activity) (context)).finish();
    }

    /**
     * Use this initWin() for win do not change this method,
     * because it will change the implementation for the high score.
     */
    private void initWin() {
        Intent intent = new Intent(context, WinActivity.class);
        scoreOutput = Integer.toString(score);
        intent.putExtra("score", scoreOutput);
        context.startActivity(intent);
        ((Activity) (context)).finish();
    }
}