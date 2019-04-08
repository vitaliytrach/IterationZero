package com.mygdx.game.components;

import com.mygdx.game.data.Tile;
import com.mygdx.game.interfaces.IComponent;

public class MapComponent implements IComponent {

    private int id;
    private String type;
    private Tile[] tiles;
    private int width, height;

    public MapComponent(int id, Tile[] tiles, int width, int height) {
        this.id = id;
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        type = "MapComponent";
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Tile getTile(int x, int y) {
        return tiles[(y * width) + x];
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
