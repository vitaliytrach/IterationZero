package com.mygdx.game.components;

import com.mygdx.game.interfaces.IComponent;

public class LocationComponent implements IComponent {

    private int id;
    private String type;
    private int map, x, y;

    public LocationComponent(int id, int map, int x, int y) {
        this.id = id;
        this.map = map;
        this.x = x;
        this.y = y;
        type = "LocationComponent";
    }

    public void setMap(int map) {
        this.map = map;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMap() { return map; }
    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public int getID() { return id; }

    @Override
    public String getType() { return type; }
}
