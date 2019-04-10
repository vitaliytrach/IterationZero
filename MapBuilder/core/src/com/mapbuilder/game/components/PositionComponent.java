package com.mapbuilder.game.components;

import com.mapbuilder.game.interfaces.IComponent;

/**
 * The PositionComponent class is responsible of holding
 * the x and y coordinates of an entity that will use this component.
 * Provides getters and setters of the x and y for easy access
 * and changing the position.
 */
public class PositionComponent implements IComponent {

    private int id;
    private String type;
    private float x, y;

    public PositionComponent(int id, float x, float y) {
        this.x = x;
        this.y = y;
        this.id = id;
        type = "PositionComponent";
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    // GETTERS

    public float getX() { return x; }

    public float getY() {
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
