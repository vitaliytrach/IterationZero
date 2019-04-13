package com.mygdx.game.builders;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.*;
import com.mygdx.game.engine.WallManager;
import com.mygdx.game.entities.Player;
import com.mygdx.game.interfaces.IBuilder;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.systems.AnimationSystem;
import com.mygdx.game.systems.CollisionDetectionSystem;
import com.mygdx.game.systems.RenderSystem;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.EntityIDs;


import java.util.ArrayList;

public class PlayerEntityBuilder implements IBuilder {

    private IEntity player;
    private SpriteBatch batch;
    private ArrayList<IComponent> componentList;
    private ArrayList<ISystem> systemList;
    private AssetManager assetManager;
    private WallManager wm;

    public PlayerEntityBuilder(SpriteBatch batch, AssetManager assetManager) {
        this.assetManager = assetManager;
        this.batch = batch;
        player = new Player(EntityIDs.PLAYER_ID);
        componentList = new ArrayList<IComponent>();
        systemList = new ArrayList<ISystem>();
        wm = WallManager.getInstance();

        buildComponentList();
        buildSystemList();
    }

    @Override
    public void buildComponentList() {

        int id = player.getID();
        Sprite playerSprite = new Sprite((Texture)assetManager.get("test_player2.png"));

        componentList.add(new TransformComponent(id, new Vector2(320,240), new Vector2(0,0), new Vector2(0,0)));
        componentList.add(new RenderComponent(id, batch));
        componentList.add(new SpriteComponent(id, playerSprite));
        componentList.add(new LocationComponent(id, 1,0,0));
        componentList.add(new AnimationComponent(id, new Texture("player_spritesheet.png")));
        componentList.add(new EntityStateComponent(id));

        wm.setWallStatus(1,0,0,true);
    }

    @Override
    public void buildSystemList() {
        systemList.add(new AnimationSystem(player.getID()));
        systemList.add(new RenderSystem(player.getID()));
        systemList.add(new CollisionDetectionSystem(player.getID()));
    }

    @Override
    public IEntity getEntity(){
        return player;
    }

    @Override
    public ArrayList<IComponent> getComponentList(){
        return componentList;
    }

    @Override
    public ArrayList<ISystem> getSystemList() {
        return systemList;
    }
}
