package com.mygdx.game.builders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.entities.Tile;
import com.mygdx.game.interfaces.IBuilder;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.systems.RenderSystem;

import java.util.ArrayList;

public class WorldEntityBuilder implements IBuilder {

    private ArrayList<IComponent> componentList;
    private ArrayList<ISystem> systemList;
    private SpriteBatch batch;
    private IEntity tile;

    public WorldEntityBuilder(IEntity tile, SpriteBatch batch) {
        componentList = new ArrayList<IComponent>();
        systemList = new ArrayList<ISystem>();

        this.tile = tile;
        this.batch = batch;

        buildComponentList();
        buildSystemList();
    }

    @Override
    public void buildComponentList() {

    }

    @Override
    public void buildSystemList() {

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
