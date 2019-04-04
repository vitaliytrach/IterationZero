package com.mygdx.game.components;

import com.mygdx.game.interfaces.IComponent;

/**
 * The PositionComponent class is responsible of holding
 * the x and y coordinates of an entity that will use this component.
 * Provides getters and setters of the x and y for easy access
 * and changing the position.
 */
public class PositionComponent implements IComponent {

    private int id;
    private String type;
    private int x, y;

    public PositionComponent(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        type = "PositionComponent";
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // GETTERS

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
