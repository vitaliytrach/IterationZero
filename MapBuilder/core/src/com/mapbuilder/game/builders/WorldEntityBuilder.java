package com.mapbuilder.game.builders;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mapbuilder.game.components.PositionComponent;
import com.mapbuilder.game.components.RenderComponent;
import com.mapbuilder.game.data.TileData;
import com.mapbuilder.game.entities.World;
import com.mapbuilder.game.interfaces.IBuilder;
import com.mapbuilder.game.interfaces.IComponent;
import com.mapbuilder.game.interfaces.IEntity;
import com.mapbuilder.game.interfaces.ISystem;
import com.mapbuilder.game.systems.RenderWorldSystem;

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
