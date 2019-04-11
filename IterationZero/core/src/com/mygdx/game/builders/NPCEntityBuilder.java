package com.mygdx.game.builders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.*;
import com.mygdx.game.interfaces.IBuilder;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.systems.RenderSystem;

import java.util.ArrayList;

public class NPCEntityBuilder implements IBuilder {

    private ArrayList<IComponent> cl;
    private ArrayList<ISystem> sl;
    private IEntity entity;
    private SpriteBatch batch;
    private int id;

    public NPCEntityBuilder(IEntity entity, SpriteBatch batch) {
        this.entity = entity;
        this.batch = batch;
        cl = new ArrayList<IComponent>();
        sl = new ArrayList<ISystem>();
        id = entity.getID();

        buildComponentList();
        buildSystemList();
    }

    @Override
    public void buildComponentList() {
        Sprite sprite = new Sprite(new Texture("test_goat.png"));

        cl.add(new SpriteComponent(id, sprite));
        cl.add(new TransformComponent(id,
                new Vector2(325,165), new Vector2(0,0), new Vector2(0,0)));
        cl.add(new LocationComponent(id, 1, 1,1));
        cl.add(new RenderComponent(id, batch));
        cl.add(new EntityStateComponent(id));
    }

    @Override
    public void buildSystemList() {
        sl.add(new RenderSystem(id));
    }

    @Override
    public ArrayList<IComponent> getComponentList() {
        return cl;
    }

    @Override
    public ArrayList<ISystem> getSystemList() {
        return sl;
    }

    @Override
    public IEntity getEntity() {
        return entity;
    }
}
