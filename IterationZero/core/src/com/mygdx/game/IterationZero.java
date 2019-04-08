package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;

	private SpriteBatch batch;
	private AssetManager assetManager;
	private ECSEngine engine;
	private Camera camera;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		engine = new ECSEngine(batch,camera);

		assetManager.load("test_player2.png", Texture.class);
		// Block for the assetManager to load since it's done asynchronously
		while(!assetManager.update());

		IEntity player = new Player(EntityIDs.PLAYER_ID);
		engine.addEntity(new PlayerEntityBuilder(player, batch, assetManager));

		int width = 100;
		int height = 100;
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
