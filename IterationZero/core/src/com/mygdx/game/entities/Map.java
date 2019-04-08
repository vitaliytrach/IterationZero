package com.mygdx.game.entities;

import com.mygdx.game.interfaces.IEntity;

public class Map implements IEntity {

    private int id;
    private String type;

    public Map(int id) {
        this.id = id;
        type = "WorldEntity";
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
