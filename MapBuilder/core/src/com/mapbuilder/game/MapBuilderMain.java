package com.mapbuilder.game;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mapbuilder.game.components.RenderComponent;
import com.mapbuilder.game.components.TransformComponent;
import com.mapbuilder.game.engine.GameAssetManager;
import com.mapbuilder.game.engine.GameEngine;
import com.mapbuilder.game.systems.RenderSystem;



public class MapBuilderMain extends ApplicationAdapter {
	private Engine engine;
	private GameAssetManager gameAssetManager;

	@Override
	public void create () {
		engine = GameEngine.getInstance().engine;

		// load asset manager
		gameAssetManager = new GameAssetManager();
		gameAssetManager.loadTextures();
		while(!gameAssetManager.assetManager.update());


		// build world
		Entity world = new Entity();
		world.add(new TransformComponent(new Vector2(0,0),new Vector2(0,0),new Vector2(0,0)));
		world.add(new RenderComponent((Texture) gameAssetManager.assetManager.get(gameAssetManager.playerTexture)));
		engine.addEntity(world);


		// add systems to engine
		engine.addSystem(new RenderSystem());
	}

	@Override
	public void render () { engine.update(Gdx.graphics.getDeltaTime());
	}

}
