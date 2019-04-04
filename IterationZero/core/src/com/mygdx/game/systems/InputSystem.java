package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.interfaces.ISystem;

public class InputSystem implements ISystem {

    private int id;
    private String type;
    private ComponentManager componentManager;

    public InputSystem(int id) {
        this.id = id;
        type = "InputSystem";
        componentManager = ComponentManager.getInstance();
    }

    @Override
    public void render() {
        PositionComponent pc = (PositionComponent) componentManager.getComponent(id, "PositionComponent");

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            pc.setX(pc.getX() - 5);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            pc.setX(pc.getX() + 5);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            pc.setY(pc.getY() + 5);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            pc.setY(pc.getY() - 5);
        }
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
