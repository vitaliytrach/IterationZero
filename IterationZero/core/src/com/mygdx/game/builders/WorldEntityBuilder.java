package com.mygdx.game.builders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.interfaces.IBuilder;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.systems.InputSystem;
import com.mygdx.game.systems.RenderSystem;

import java.util.ArrayList;

public class WorldEntityBuilder implements IBuilder {

    private ArrayList<IComponent> componentList;
    private ArrayList<ISystem> systemList;
    private SpriteBatch batch;
    private IEntity tile;
    private float x, y;

    public WorldEntityBuilder(IEntity tile, SpriteBatch batch, float x, float y) {
        componentList = new ArrayList<IComponent>();
        systemList = new ArrayList<ISystem>();
        this.x = x;
        this.y = y;

        this.tile = tile;
        this.batch = batch;

        buildComponentList();
        buildSystemList();
    }

    @Override
    public void buildComponentList() {

        componentList.add(new PositionComponent(tile.getID(), x, y));
        componentList.add(new RenderComponent(tile.getID(), batch));
        componentList.add(new SpriteComponent(tile.getID(), new Sprite(new Texture("test_tile.png"))));
    }

    @Override
    public void buildSystemList() {
        systemList.add(new RenderSystem(tile.getID()));
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
        return tile;
    }
}
