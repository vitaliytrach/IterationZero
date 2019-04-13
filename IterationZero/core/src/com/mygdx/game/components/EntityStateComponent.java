package com.mygdx.game.components;

import com.mygdx.game.interfaces.IComponent;

public class EntityStateComponent implements IComponent {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;

    private int id;
    private String type;
    private boolean[] availableDirections;
    private boolean moving;
    private String direction;

    public EntityStateComponent(int id) {
        this.id = id;
        type = "EntityStateComponent";
        moving = false;
        direction = "down";

        availableDirections = new boolean[4];
        availableDirections[UP] = true;
        availableDirections[DOWN] = true;
        availableDirections[RIGHT] = true;
        availableDirections[LEFT] = true;
    }

    public boolean hasNeighbor(int index) {
        return !availableDirections[index];
    }

    public void setAvailableDirections(boolean[] availableDirections) {
        this.availableDirections = availableDirections;
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
