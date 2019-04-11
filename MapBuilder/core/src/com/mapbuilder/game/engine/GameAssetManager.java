package com.mapbuilder.game.engine;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class GameAssetManager {

    private static GameAssetManager instance;
    public final AssetManager assetManager;

    // Later implement looping to get files
    public final String playerTexture = "Textures/test_tile.png";

    public GameAssetManager() {
        assetManager = new AssetManager();
    }

    public static GameAssetManager getInstance() {
        if(instance == null) {
            instance = new GameAssetManager();
        }
        return instance;
    }

    public void loadTextures() {
        assetManager.load(playerTexture, Texture.class);
    }
}