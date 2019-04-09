package com.mapbuilder.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mapbuilder.game.builders.PlayerEntityBuilder;
import com.mapbuilder.game.builders.WorldEntityBuilder;
import com.mapbuilder.game.data.TileData;
import com.mapbuilder.game.engine.ECSEngine;
import com.mapbuilder.game.entities.Player;
import com.mapbuilder.game.interfaces.IEntity;
import com.mapbuilder.game.utils.EntityIDs;
import com.mapbuilder.game.utils.GenerateMap;



public class MapBuilderMain extends ApplicationAdapter {
	private SpriteBatch batch;
	private AssetManager assetManager;
	private ECSEngine engine;

	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		engine = ECSEngine.getInstance(batch);

		assetManager.load("test_player.png", Texture.class);
		// Block for the assetManager to load since it's done asynchronously
		while(!assetManager.update());

		IEntity player = new Player(EntityIDs.PLAYER_ID);
		engine.addEntity(new PlayerEntityBuilder(player, batch, assetManager));

		int width = 10;
		int height = 10;
		TileData[] world = GenerateMap.generateMap(width, height);
		WorldEntityBuilder builder = new WorldEntityBuilder(world, batch, width, height);
		engine.addEntity(builder);
	}

	@Override
	public void render () {
		engine.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
