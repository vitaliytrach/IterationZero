package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.builders.PlayerEntityBuilder;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.engine.ECSEngine;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.Tile;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.systems.InputSystem;
import com.mygdx.game.systems.RenderSystem;
import com.mygdx.game.utils.EntityIDs;

import java.util.ArrayList;

public class IterationZero extends ApplicationAdapter {
	private SpriteBatch batch;
	private AssetManager assetManager;
	private ECSEngine engine;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		engine = ECSEngine.getInstance();

		IEntity player = new Player(EntityIDs.PLAYER_ID);
		engine.addEntity(new PlayerEntityBuilder(player, batch));
	}

	@Override
	public void render () {
		engine.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
