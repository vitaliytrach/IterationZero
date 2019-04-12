package com.mapbuilder.game.builders;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mapbuilder.game.components.RenderComponent;
import com.mapbuilder.game.components.TransformComponent;
import com.mapbuilder.game.engine.GameAssetManager;
import com.mapbuilder.game.engine.GameEngine;

public class WorldBuilder {

    private Engine engine;
    private GameAssetManager gameAssetManager;
    private int worldHeight = 10;
    private int worldWidth = 10;

    public WorldBuilder() {
        engine = GameEngine.getInstance().engine;
        gameAssetManager = GameAssetManager.getInstance();
    }

    public void buildStarterWorld() {

        Texture basicTileTexture = gameAssetManager.assetManager.get(gameAssetManager.tileTexture);
        Sprite basicTileSprite = new Sprite(basicTileTexture);
        float tileHeight = basicTileSprite.getHeight();
        float tileWidth = basicTileSprite.getWidth();

        Entity world = new Entity();
        world.add(new TransformComponent(new Vector2(Gdx.graphics.getHeight() / 2, Gdx.graphics.getHeight() / 2), new Vector2(0, 0), new Vector2(0, 0)));
        world.add(new RenderComponent(basicTileSprite));
        engine.addEntity(world);

    }
}
