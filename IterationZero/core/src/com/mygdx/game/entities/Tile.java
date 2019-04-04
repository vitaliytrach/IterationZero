package com.mygdx.game.entities;

import com.mygdx.game.interfaces.IEntity;

public class Tile implements IEntity {

    public static final int DEFAULT_TILE_WIDTH = 128;
    public static final int DEFAULT_TILE_HEIGHT = 64;

    private int id;
    private String type;

    public Tile(int id) {
        this.id = id;
        type = "TileEntity";
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
