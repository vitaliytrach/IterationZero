package com.mapbuilder.game.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mapbuilder.game.interfaces.IComponent;

/**
 * The RenderComponent is responsible of holding the SpriteBatch
 * that an entity can use.
 */
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
