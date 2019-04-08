package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.data.TileData;
import com.mygdx.game.entities.Tile;

public class GenerateMap {

    public static TileData[] generateMap(int width, int height) {

        TileData[] tiles = new TileData[width * height];

        int offsetX = 0;
        int offsetY = 0;

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

        //buildJsonText(tiles, width, height);

        return tiles;
    }

    private static void buildJsonText(TileData[] tiles, int width, int height) {

        StringBuilder jsonText = new StringBuilder();
        jsonText.append("[\n");

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                jsonText.append("\t{\n");
                jsonText.append("\t\t\"x\" : " + tiles[(j*width) + i].getX() + ",\n");
                jsonText.append("\t\t\"y\" : " + tiles[(j*width) + i].getY() + ",\n");
                jsonText.append("\t\t\"sprite\" : \"" + tiles[(j*width) + i].getSpriteFilePath() + "\"\n");
                jsonText.append("\t},\n");
            }
        }

        jsonText.deleteCharAt(jsonText.length() - 2);
        jsonText.append("]");

        FileHandle file = Gdx.files.local("maps/1.json");
        file.writeString(jsonText.toString(), false);
    }
}
