package com.mygdx.game.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.interfaces.IComponent;

public class AnimationComponent implements IComponent {

    private static final int SPRITESHEET_WIDTH = 10;
    private static final int SPRITESHEET_HEIGHT = 2;

    private int id;
    private String type;
    private Texture animationSheet;
    private Animation<TextureRegion> animation;
    private TextureRegion[][] spritesheet;

    public AnimationComponent(int id, Texture animationSheet) {
        this.id = id;
        this.animationSheet = animationSheet;
        type = "AnimationComponent";

        init();
    }

    private void init() {

        spritesheet = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / SPRITESHEET_WIDTH,
                animationSheet.getHeight() / SPRITESHEET_HEIGHT);

        TextureRegion[] walkFrames = new TextureRegion[SPRITESHEET_WIDTH * SPRITESHEET_HEIGHT];
        int index = 0;
        for (int i = 0; i < SPRITESHEET_HEIGHT; i++) {
            for (int j = 0; j < SPRITESHEET_WIDTH; j++) {
                walkFrames[index++] = spritesheet[i][j];
            }
        }
        animation = new Animation<TextureRegion>(32f/60f, walkFrames);
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
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
