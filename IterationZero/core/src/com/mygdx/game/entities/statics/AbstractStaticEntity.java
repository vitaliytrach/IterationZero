package com.mygdx.game.entities.statics;

import com.mygdx.game.interfaces.IEntity;

public abstract class AbstractStaticEntity implements IEntity {

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
        return true;
    }
}
