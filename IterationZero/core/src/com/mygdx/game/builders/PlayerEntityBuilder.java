package com.mygdx.game.builders;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.interfaces.IBuilder;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.systems.RenderSystem;
import com.badlogic.gdx.math.Vector2;



import java.util.ArrayList;

public class PlayerEntityBuilder implements IBuilder {

    private IEntity player;
    private SpriteBatch batch;
    private ArrayList<IComponent> componentList;
    private ArrayList<ISystem> systemList;
    private AssetManager assetManager;

    public PlayerEntityBuilder(IEntity player, SpriteBatch batch, AssetManager assetManager) {
        this.assetManager = assetManager;
        this.batch = batch;
        this.player = player;
        componentList = new ArrayList<IComponent>();
        systemList = new ArrayList<ISystem>();
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
        componentList.add(new LocationComponent(id, 0,1,1));
    }

    @Override
    public void buildSystemList() {
        systemList.add(new RenderSystem(player.getID()));
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
