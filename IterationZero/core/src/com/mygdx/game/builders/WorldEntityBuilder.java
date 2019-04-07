package com.mygdx.game.builders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.data.TileData;
import com.mygdx.game.entities.World;
import com.mygdx.game.interfaces.IBuilder;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.systems.InputSystem;
import com.mygdx.game.systems.RenderSystem;
import com.mygdx.game.systems.RenderWorldSystem;

import java.util.ArrayList;

public class WorldEntityBuilder implements IBuilder {

    private ArrayList<IComponent> componentList;
    private ArrayList<ISystem> systemList;
    private SpriteBatch batch;
    private World world;
    private int id = 5;

    public WorldEntityBuilder(TileData[] tiles, SpriteBatch batch, int width, int height) {
        componentList = new ArrayList<IComponent>();
        systemList = new ArrayList<ISystem>();
        world = new World(id, tiles, width, height);
        this.batch = batch;

        buildComponentList();
        buildSystemList();
    }

    @Override
    public void buildComponentList() {
        componentList.add(new PositionComponent(id, 340, 480));
        componentList.add(new RenderComponent(id, batch));
    }

    @Override
    public void buildSystemList() {
        systemList.add(new RenderWorldSystem(id));
        //systemList.add(new InputSystem(id, world));
    }

    @Override
    public ArrayList<IComponent> getComponentList() {
        return componentList;
    }

    @Override
    public ArrayList<ISystem> getSystemList() {
        return systemList;
    }

    @Override
    public IEntity getEntity() {
        return world;
    }
}
