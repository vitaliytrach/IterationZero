package com.mygdx.game.components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.interfaces.IComponent;

public class SpriteComponent implements IComponent {

    private int id;
    private String type;
    private Sprite sprite;

    public SpriteComponent(int id, Sprite sprite) {
        this.id = id;
        this.sprite = sprite;
        type = "SpriteComponent";
    }

    public Sprite getSprite() {
        return sprite;
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
