package com.mygdx.game.systems;

import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.EntityManager;
import com.mygdx.game.interfaces.ISystem;

public class RenderSystem implements ISystem {

    private int id;
    private String type;
    private ComponentManager componentManager;
    private EntityManager entityManager;

    public RenderSystem(int id) {
        this.id = id;
        type = "RenderSystem";
        componentManager = ComponentManager.getInstance();
        entityManager = EntityManager.getInstance();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void render() {
        // Roses are red,
        // Violets are blue,
        // render method?
        // TODO
    }
}
