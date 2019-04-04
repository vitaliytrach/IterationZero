package com.mygdx.game.entities;

import com.mygdx.game.interfaces.IEntity;

public class Tile implements IEntity {

    private int id;
    private String type;

    public Tile(int id) {
        this.id = id;
        type = "TileEntity";
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public String getType() {
        return type;
    }
}
