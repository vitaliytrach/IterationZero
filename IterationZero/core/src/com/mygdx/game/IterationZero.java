package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.builders.PlayerEntityBuilder;
import com.mygdx.game.builders.WorldEntityBuilder;
import com.mygdx.game.data.TileData;
import com.mygdx.game.engine.ECSEngine;
import com.mygdx.game.entities.Player;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.utils.EntityIDs;
import com.mygdx.game.utils.GenerateMap;

public class IterationZero extends ApplicationAdapter {
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
