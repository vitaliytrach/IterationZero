package com.mygdx.game.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.interfaces.IComponent;

public class AnimationComponent implements IComponent {

    private static final int SPRITESHEET_WIDTH = 10;
    private static final int SPRITESHEET_HEIGHT = 3;

    private int id;
    private String type;
    private Texture animationSheet;
    private Animation<TextureRegion> animation;
    private TextureRegion[][] spritesheet;

    public AnimationComponent(int id, Texture animationSheet) {
        this.id = id;
        this.animationSheet = animationSheet;
        type = "AnimationComponent";
        spritesheet = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / SPRITESHEET_WIDTH,
                animationSheet.getHeight() / SPRITESHEET_HEIGHT);
    }

    public TextureRegion getFrame(int x, int y) {
        return spritesheet[x][y];
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
