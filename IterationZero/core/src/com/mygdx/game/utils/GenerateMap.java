package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.data.TileData;
import com.mygdx.game.entities.Tile;

import java.util.HashSet;

public class GenerateMap {

    public static TileData[] generateMap(int width, int height) {

        TileData[] tiles = new TileData[width * height];

        int offsetX = 340;
        int offsetY = 480;

        int x,y;

        for(int i = 0; i < height; i++) {
            int xPos = offsetX;
            int yPos = offsetY;

            for(int j = 0; j < width; j++) {
                x = xPos;
                y = yPos;

                tiles[(j * width) + i] = new TileData(x, y);

                xPos += Tile.DEFAULT_TILE_WIDTH / 2;
                yPos -= Tile.DEFAULT_TILE_HEIGHT / 2;
            }

            offsetX -= Tile.DEFAULT_TILE_WIDTH / 2;
            offsetY -= Tile.DEFAULT_TILE_HEIGHT / 2;
        }

        return tiles;
    }
}
