package com.mygdx.game.components;

import com.mygdx.game.interfaces.IComponent;

public class EntityStateComponent implements IComponent {

    private int id;
    private String type;
    private boolean moving;
    private String direction;

    public EntityStateComponent(int id) {
        this.id = id;
        type = "EntityStateComponent";
        moving = false;
        direction = "down";
    }

    public String getDirection() {
        return direction;
    }

    public void changeDirection(String direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoveStatus(boolean moving) {
        this.moving = moving;
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
