package com.mapbuilder.game;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.mapbuilder.game.engine.GameAssetManager;
import com.mapbuilder.game.engine.GameEngine;
import com.mapbuilder.game.systems.GameMapRenderer;
import com.mapbuilder.game.systems.RenderSystem;


public class MapBuilderMain extends ApplicationAdapter {
	private Engine engine;
	private GameAssetManager gameAssetManager;

	@Override
	public void create () {
		engine = GameEngine.getInstance().engine;

		// load asset manager
		GameAssetManager.getInstance().loadTextures();
		GameAssetManager.getInstance().loadMap("FirstMap");
		while(!GameAssetManager.getInstance().assetManager.update()) { System.out.println("Loading"); }


		// build world
		//WorldBuilder worldStart = new WorldBuilder();
		//worldStart.buildStarterWorld();


		// add systems to engine
		engine.addSystem(new GameMapRenderer());
		engine.addSystem(new RenderSystem());
	}

	@Override
	public void render () { engine.update(Gdx.graphics.getDeltaTime());
	}

}
