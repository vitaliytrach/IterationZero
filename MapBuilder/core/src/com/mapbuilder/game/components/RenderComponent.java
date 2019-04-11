package com.mapbuilder.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mapbuilder.game.engine.GameAssetManager;

public class RenderComponent implements Component {

    public Texture texture;

    public RenderComponent(Texture texture){
        this.texture = texture;
    }





}
