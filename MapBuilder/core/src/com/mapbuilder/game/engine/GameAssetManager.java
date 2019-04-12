package com.mapbuilder.game.engine;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TideMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class GameAssetManager {

    private static GameAssetManager instance;
    public AssetManager assetManager;

    // Later implement looping to get files
    public final String tileTexture = "Textures/test_tile.png";

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
        assetManager.load(tileTexture, Texture.class);
    }

    public void loadMap(String mapName) {
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("maps/" + mapName + ".tmx", TiledMap.class);
    }
}