package com.mygdx.game.entities;

import com.mygdx.game.interfaces.IEntity;

public class Player implements IEntity {

    private int id;
    private String type;

    public Player(int id) {
        this.id = id;
        type = "PlayerEntity";
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isStatic() {
        return false;
    }
}
