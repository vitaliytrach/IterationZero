package com.mygdx.game.entities;

import com.mygdx.game.interfaces.IEntity;

public class Goat implements IEntity {

    private int id;
    private String type;

    public Goat(int id) {
        this.id = id;
        type = "GoatEntity";
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
