package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.interfaces.ISystem;

public class AttackSystem implements ISystem {

    public static final float ATTACK_TIME = 0.5f;

    private int id;
    private String type;
    private ComponentManager cm;
    private float deltaTime;

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
            if(Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT) && !esc.isAttacking()) {
                deltaTime = 0;
                esc.setAttackStatus(true);
            }

            deltaTime = deltaTime + Gdx.graphics.getDeltaTime();

            if(esc.isAttacking() && deltaTime > ATTACK_TIME) {
                esc.setAttackStatus(false);
            }
        }
    }
}
