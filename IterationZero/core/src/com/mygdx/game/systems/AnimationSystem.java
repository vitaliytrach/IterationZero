package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.components.AnimationComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.interfaces.ISystem;

public class AnimationSystem implements ISystem {

    private int id;
    private String type;
    private ComponentManager cm;
    private float stateTime = 0f;

    public AnimationSystem(int id) {
        this.id = id;
        cm = ComponentManager.getInstance();
        type = "AnimationSystem";
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
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = ac.getAnimation().getKeyFrame(stateTime, true);
        sc.setSprite(new Sprite(currentFrame));
    }
}
