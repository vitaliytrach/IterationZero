package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.components.AnimationComponent;
import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.interfaces.ISystem;

public class AnimationSystem implements ISystem {

    public static final int MOVEMENT_FRAMES = 4;
    public static final int ATTACK_FRAMES = 2;

    private int id;
    private String type;
    private ComponentManager cm;
    private int switchTime;
    private float attackTime;
    private float deltaTime;
    private float attack = 0;
    private int counter;
    private int offset, startX, startY;

    public AnimationSystem(int id) {
        this.id = id;
        cm = ComponentManager.getInstance();
        type = "AnimationSystem";
        switchTime = MovementSystem.TICKS_PER_BLOCK_MOVEMENT / MOVEMENT_FRAMES;
        attackTime = AttackSystem.ATTACK_TIME / (float) ATTACK_FRAMES;
        deltaTime = 0;
        counter = 0;
        offset = 0;
        startX = 0;
        startY = 0;
    }

    @Override
    public void render() {
        SpriteComponent sc = (SpriteComponent) cm.getComponent(id, "SpriteComponent");
        AnimationComponent ac = (AnimationComponent) cm.getComponent(id, "AnimationComponent");
        EntityStateComponent esc = (EntityStateComponent) cm.getComponent(id, "EntityStateComponent");

        if(esc.isAttacking()) {
            attackAnimation(esc.getDirection(), sc, ac);
            return;
        }

        if(esc.getDirection() == "up") {
            startX = 5;
            startY = 1;
        } else if(esc.getDirection() == "down") {
            startX = 0;
            startY = 0;
        } else if(esc.getDirection() == "right") {
            startX = 5;
            startY = 0;
        } else if(esc.getDirection() == "left") {
            startX = 0;
            startY = 1;
        }

        if(esc.isMoving()) {

            if(counter >= 32) {
                offset = 0;
                counter = 0;
            }

            if(counter % switchTime == 0) {
                sc.setSprite(new Sprite(ac.getFrame(startY, startX + offset)));
                offset++;
            }

            counter++;
        } else {
            sc.setSprite(new Sprite(ac.getFrame(startY, startX)));
        }
    }

    private void attackAnimation(String direction, SpriteComponent sc, AnimationComponent ac) {

        if(direction == "up") {
            startX = 6;
            startY = 2;
        } else if(direction == "down") {
            startX = 0;
            startY = 2;
        } else if(direction == "right") {
            startX = 4;
            startY = 2;
        } else if(direction == "left") {
            startX = 2;
            startY = 2;
        }

        deltaTime += Gdx.graphics.getDeltaTime();

        if(deltaTime >= attack) {
            sc.setSprite(new Sprite(ac.getFrame(startY, startX + offset)));

            if(offset > 0) {
                offset = 0;
            } else {
                offset = 1;
            }

            attack += attackTime;
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
