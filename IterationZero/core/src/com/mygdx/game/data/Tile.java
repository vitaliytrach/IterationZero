package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tile {

    public static final int DEFAULT_TILE_WIDTH = 128;
    public static final int DEFAULT_TILE_HEIGHT = 64;

    private float x;
    private float y;
    private Sprite tileSprite;

    public Tile(float x, float y, String filepath) {
        this.tileSprite = new Sprite(new Texture(filepath));
        this.x = x;
        this.y = y;
    }

    // GETTERS & SETTERS

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Sprite getTileSprite() {
        return tileSprite;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
