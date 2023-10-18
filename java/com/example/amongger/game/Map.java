package com.example.amongger.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.amongger.R;

public class Map {
    //Display metrics
    private final int width;
    private final int height;
    //Tiles used for tilemapping. 12 x 21 grid.
    private int[] tiles = {R.drawable.goal1, R.drawable.goal3, R.drawable.goal4,
        R.drawable.button_tile, R.drawable.goal5, R.drawable.goal2, R.drawable.river,
        R.drawable.road2, R.drawable.start1, R.drawable.start2, R.drawable.start3};
    private Bitmap[] bitTiles;
    private Bitmap tile;
    private MapLayoutManager mapManager;
    private int[][] mapLayout;
    private Context context;
    public Map(Context c, int dispWidth, int dispHeight, String lvl) {
        this.context = c;
        this.width = dispWidth;
        this.height = dispHeight;
        mapManager = new MapLayoutManager(lvl);
        this.mapLayout = mapManager.getMapLayout();
        initMapTiles();
    }

    public Bitmap mapDraw(int c, int r) {
        //Chaotically draws map based on the tileLayout loaded.
        switch (mapLayout[c][r]) {
        case 1:
            tile = bitTiles[1];
            break;
        case 2:
            tile = bitTiles[2];
            break;
        case 3:
            tile = bitTiles[3];
            break;
        case 4:
            tile = bitTiles[4];
            break;
        case 5:
            tile = bitTiles[5];
            break;
        case 6:
            tile = bitTiles[6];
            break;
        case 7:
            tile = bitTiles[7];
            break;
        case 8:
            tile = bitTiles[8];
            break;
        case 9:
            tile = bitTiles[9];
            break;
        case 10:
            tile = bitTiles[10];
            break;
        default:
            tile = bitTiles[0];
            break;
        }
        return tile;
    }
    public void initMapTiles() {
        bitTiles = new Bitmap[] {Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(context.getResources(),
                tiles[0]), width / 12, height / 21,
            false), Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(context.getResources(),
                tiles[1]), width / 12, height / 21,
            false),
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(),
                    tiles[2]), width / 12, height / 21,
                false),
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(),
                    tiles[3]), width / 12, height / 21,
                false),
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(),
                    tiles[4]), width / 12, height / 21,
                false),
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(),
                    tiles[5]), width / 12, height / 21,
                false),
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(),
                    tiles[6]), width / 12, height / 21,
                false),
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(),
                    tiles[7]), width / 12, height / 21,
                false),
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(),
                    tiles[8]), width / 12, height / 21,
                false),
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(),
                    tiles[9]), width / 12, height / 21,
                false),
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.getResources(),
                    tiles[10]), width / 12, height / 21,
                false)};
    }
}
