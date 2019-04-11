package com.mygdx.game.entities.npcs;

import com.mygdx.game.interfaces.IEntity;

public abstract class AbstractNPCEntity implements IEntity {

    protected int id;
    protected String type;

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
