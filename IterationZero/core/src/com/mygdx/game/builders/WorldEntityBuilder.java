package com.mygdx.game.builders;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.MapComponent;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.data.Tile;
import com.mygdx.game.entities.Map;
import com.mygdx.game.interfaces.IBuilder;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.systems.MovementSystem;
import com.mygdx.game.systems.RenderWorldSystem;
import com.mygdx.game.utils.EntityIDs;
import com.mygdx.game.utils.JsonUtil;

import java.util.ArrayList;

public class WorldEntityBuilder implements IBuilder {

    private ArrayList<IComponent> componentList;
    private ArrayList<ISystem> systemList;
    private SpriteBatch batch;
    private int id;
    private IEntity map;

    public WorldEntityBuilder(SpriteBatch batch) {
        componentList = new ArrayList<IComponent>();
        systemList = new ArrayList<ISystem>();
        id = EntityIDs.WORLD_ID;
        this.batch = batch;
        map = new Map(id);

        buildComponentList();
        buildSystemList();
    }

    @Override
    public void buildComponentList() {
        int width = 100;
        int height = 100;
        Tile[] tiles = JsonUtil.getMap("maps/1.json");

        componentList.add(new MapComponent(id, tiles, width, height));
        componentList.add(new PositionComponent(id, 340, 480));
        componentList.add(new RenderComponent(id, batch));
    }

    @Override
    public void buildSystemList() {
        systemList.add(new RenderWorldSystem(id));
        systemList.add(new MovementSystem(id));
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
        return map;
    }
}
