package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.interfaces.ISystem;

public class AttackSystem implements ISystem {

    private int id;
    private String type;
    private ComponentManager cm;

    public AttackSystem(int id) {
        this.id = id;
        type = "AttackSystem";
        cm = ComponentManager.getInstance();
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

        EntityStateComponent esc = (EntityStateComponent) cm.getComponent(id, "EntityStateComponent");

        if(!esc.isMoving()) {
            if(Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT)) {
                esc.setAttackStatus(true);
            } else {
                esc.setAttackStatus(false);
            }
        }
    }
}
