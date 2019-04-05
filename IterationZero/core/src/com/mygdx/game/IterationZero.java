package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.builders.PlayerEntityBuilder;
import com.mygdx.game.builders.WorldEntityBuilder;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.data.TileData;
import com.mygdx.game.engine.ECSEngine;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.Tile;
import com.mygdx.game.interfaces.IBuilder;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.systems.InputSystem;
import com.mygdx.game.systems.RenderSystem;
import com.mygdx.game.utils.EntityIDs;
import com.mygdx.game.utils.GenerateMap;
import com.mygdx.game.utils.JsonUtil;

import java.util.ArrayList;

public class IterationZero extends ApplicationAdapter {
	private SpriteBatch batch;
	private AssetManager assetManager;
	private ECSEngine engine;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		engine = ECSEngine.getInstance();

		assetManager.load("test_player.png", Texture.class);
		// Block for the assetManager to load since it's done asynchronously
		while(!assetManager.update());

		IEntity player = new Player(EntityIDs.PLAYER_ID);
		engine.addEntity(new PlayerEntityBuilder(player, batch, assetManager));

		int width = 10;
		int height = 10;
		TileData[] map = GenerateMap.generateMap(width, height);

		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				IEntity tile = new Tile(5 + ((i * width) + j));
				IBuilder worldBuilder = new WorldEntityBuilder(tile, batch, map[(i * width) + j].getX(), map[(i * width) + j].getY());
				engine.addEntity(worldBuilder);
			}
		}
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
