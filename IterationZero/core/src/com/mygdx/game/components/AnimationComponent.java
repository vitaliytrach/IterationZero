package com.mygdx.game.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.interfaces.IComponent;

public class AnimationComponent implements IComponent {

    private int id;
    private String type;
    private Texture animationSheet;
    private Animation<TextureRegion> animation;

    public AnimationComponent(int id, Texture animationSheet) {
        this.id = id;
        this.animationSheet = animationSheet;
        type = "AnimationComponent";

        init();
    }

    private void init() {

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
