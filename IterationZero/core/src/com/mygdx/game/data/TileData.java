package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TileData {
    private float x;
    private float y;
    private Sprite tileSprite;

    public TileData(float x, float y) {
        this.tileSprite = new Sprite(new Texture("test_tile.png"));
        this.x = x;
        this.y = y;
    }

    // GETTERS

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
