package com.mygdx.game.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.interfaces.IComponent;

public class RenderComponent implements IComponent {

    private int id;
    private String type;
    private SpriteBatch batch;

    public RenderComponent(int id, SpriteBatch batch) {
        this.id = id;
        this.batch = batch;
        type = "RenderComponent";
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
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
