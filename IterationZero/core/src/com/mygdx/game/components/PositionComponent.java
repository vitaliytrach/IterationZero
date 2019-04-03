package com.mygdx.game.components;

import com.mygdx.game.interfaces.IComponent;

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

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }
}
