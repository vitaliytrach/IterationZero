package com.mapbuilder.game.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TileData {
    private float x;
    private float y;
    private Sprite tileSprite;
    private String spriteFilePath = "test_tile.png";

    public TileData(float x, float y) {
        this.tileSprite = new Sprite(new Texture(spriteFilePath));
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

    public String getSpriteFilePath() { return spriteFilePath; }

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
