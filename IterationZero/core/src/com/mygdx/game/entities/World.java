package com.mygdx.game.entities;

import com.mygdx.game.data.TileData;
import com.mygdx.game.interfaces.IEntity;

public class World implements IEntity {

    private int id, width, height;
    private String type;
    private TileData[] tiles;

    public World(int id, TileData[] tiles, int width, int height) {
        this.id = id;
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        type = "WorldEntity";
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public TileData getTile(int x, int y) {
        return tiles[(y * width) + x];
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }
}
