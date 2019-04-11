package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.components.AnimationComponent;
import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.interfaces.ISystem;

import java.util.concurrent.TimeUnit;

public class AnimationSystem implements ISystem {

    public static final int MOVEMENT_FRAMES = 4;

    private int id;
    private String type;
    private ComponentManager cm;
    private float stateTime = 0f;
    private int switchTime;
    private int counter;
    private int xOffset, yOffset, startX, startY;

    public AnimationSystem(int id) {
        this.id = id;
        cm = ComponentManager.getInstance();
        type = "AnimationSystem";
        switchTime = MovementSystem.TICKS_PER_BLOCK_MOVEMENT / MOVEMENT_FRAMES;
        counter = 0;
        xOffset = 0;
        yOffset = 0;
        startX = 0;
        startY = 0;
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
        SpriteComponent sc = (SpriteComponent) cm.getComponent(id, "SpriteComponent");
        AnimationComponent ac = (AnimationComponent) cm.getComponent(id, "AnimationComponent");
        EntityStateComponent esc = (EntityStateComponent) cm.getComponent(id, "EntityStateComponent");

        if(esc.isMoving()) {
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

            counter++;

            if(counter >= 32) {
                yOffset = 0;
                xOffset = 0;
                counter = 0;
            }

            if(counter % switchTime == 0) {
                xOffset++;
                sc.setSprite(new Sprite(ac.getFrame(startY, startX + xOffset)));
            }
        } else {
            yOffset = 0;
            counter = 0;
            xOffset = 0;
            sc.setSprite(new Sprite(ac.getFrame(startY, startX)));
        }
    }
}
