package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.engine.ECSEngine;
import com.mygdx.game.entities.Player;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.systems.RenderSystem;

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
		//img = new Texture("badlogic.jpg");
		//assetManager.load("/test_player.png", Texture.class);

		// TESTING STUFF
		int playerId = 0;
		IEntity player = new Player(playerId);
		Sprite playerSprite = new Sprite(new Texture("test_player.png"));


		ArrayList<IComponent> cl = new ArrayList<IComponent>();
		cl.add(new PositionComponent(playerId,50, 50));
		cl.add(new SpriteComponent(playerId, playerSprite));
		cl.add(new RenderComponent(playerId, batch));

		ArrayList<ISystem> sl = new ArrayList<ISystem>();
		sl.add(new RenderSystem(playerId));

		engine.addEntity(player, cl, sl);
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
