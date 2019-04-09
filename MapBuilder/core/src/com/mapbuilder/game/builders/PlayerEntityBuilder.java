package com.mapbuilder.game.builders;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mapbuilder.game.components.RenderComponent;
import com.mapbuilder.game.components.SpriteComponent;
import com.mapbuilder.game.components.TransformComponent;
import com.mapbuilder.game.entities.Player;
import com.mapbuilder.game.interfaces.IBuilder;
import com.mapbuilder.game.interfaces.IComponent;
import com.mapbuilder.game.interfaces.IEntity;
import com.mapbuilder.game.interfaces.ISystem;
import com.mapbuilder.game.systems.InputSystem;
import com.mapbuilder.game.systems.RenderSystem;

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
        Sprite playerSprite = new Sprite((Texture)assetManager.get("test_player.png"));

        //componentList.add(new PositionComponent(id, 340f, 220f));
        componentList.add(new TransformComponent(id, new Vector2(0,0), new Vector2(0,0), new Vector2(0,0)));
        componentList.add(new RenderComponent(id, batch));
        componentList.add(new SpriteComponent(id, playerSprite));
    }

    @Override
    public void buildSystemList() {

        int id = player.getID();

        systemList.add(new RenderSystem(id));
        systemList.add(new InputSystem(id, (Player) player));
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
