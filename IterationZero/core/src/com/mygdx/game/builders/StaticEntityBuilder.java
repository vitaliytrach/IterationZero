package com.mygdx.game.builders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.engine.WallManager;
import com.mygdx.game.interfaces.IBuilder;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.systems.RenderSystem;

import java.util.ArrayList;

public class StaticEntityBuilder implements IBuilder {

    private IEntity entity;
    private ArrayList<IComponent> cl;
    private ArrayList<ISystem> sl;
    private SpriteBatch batch;
    private WallManager wm;
    private int id;

    public StaticEntityBuilder(IEntity entity, SpriteBatch batch) {
        this.batch = batch;
        this.entity = entity;
        cl = new ArrayList<IComponent>();
        sl = new ArrayList<ISystem>();
        id = entity.getID();
        wm = WallManager.getInstance();

        buildComponentList();
        buildSystemList();
    }

    @Override
    public void buildComponentList() {

        cl.add(new TransformComponent(id,
                new Vector2(390,220), new Vector2(0,0), new Vector2(0,0)));
        cl.add(new LocationComponent(id, 1, 0,1));
        cl.add(new SpriteComponent(id, new Sprite(new Texture("test_tree.png"))));
        cl.add(new RenderComponent(id, batch));

        wm.setWallStatus(1,1,0,true);
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
