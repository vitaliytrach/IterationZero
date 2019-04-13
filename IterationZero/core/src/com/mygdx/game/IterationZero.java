package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.builders.NPCEntityBuilder;
import com.mygdx.game.builders.PlayerEntityBuilder;
import com.mygdx.game.builders.StaticEntityBuilder;
import com.mygdx.game.builders.WorldEntityBuilder;
import com.mygdx.game.engine.ECSEngine;
import com.mygdx.game.entities.npcs.Goat;
import com.mygdx.game.entities.statics.TreeEntity;
import com.mygdx.game.utils.EntityIDs;

public class IterationZero extends ApplicationAdapter {

	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;

	private SpriteBatch batch;
	private AssetManager assetManager;
	private ECSEngine engine;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		engine = new ECSEngine(batch);

		assetManager.load("test_player2.png", Texture.class);
		assetManager.load("test_tile.png", Texture.class);
		// Block for the assetManager to load since it's done asynchronously
		while(!assetManager.update());

		engine.addEntity(new WorldEntityBuilder(batch, assetManager));
		engine.addEntity(new PlayerEntityBuilder(batch, assetManager));
		engine.addEntity(new StaticEntityBuilder(new TreeEntity(EntityIDs.getNextID()), batch));
		engine.addEntity(new NPCEntityBuilder(new Goat(EntityIDs.getNextID()), batch));
	}

	@Override
	public void render () {
		engine.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}
}
