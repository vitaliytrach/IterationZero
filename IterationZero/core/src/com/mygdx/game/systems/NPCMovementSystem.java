package com.mygdx.game.systems;

import com.mygdx.game.interfaces.ISystem;

public class NPCMovementSystem implements ISystem {

    private int id;
    private String type;

    public NPCMovementSystem(int id) {
        this.id = id;
        type = "NPCMovementSystem";
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
    public void render() {

    }
}
