package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.EntityManager;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.EntityRenderOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RenderSystem implements ISystem {

    private int id;
    private String type;
    private ComponentManager componentManager;
    private EntityManager entityManager;

    public RenderSystem(int id) {
        this.id = id;
        type = "RenderSystem";
        componentManager = ComponentManager.getInstance();
        entityManager = EntityManager.getInstance();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void render() {
        // This line looks messy, I'll fix it later once I get stuff rendering
        SpriteBatch batch = ((RenderComponent) componentManager.getComponent(id, "RenderComponent")).getSpriteBatch();

        HashMap<Integer, IEntity> entities = entityManager.getEntites();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        if(entities != null) {
            Set<Map.Entry<Integer,IEntity>> entitiesMap = entities.entrySet();

            for(String s : EntityRenderOrder.getRenderOrder()) {

                HashMap<Integer, IEntity> e1 = entityManager.getEntityTypes(s);


                for(Map.Entry<Integer,IEntity> e : e1.entrySet()) {

                    int id = e.getValue().getID();

                    Sprite sprite = ((SpriteComponent) componentManager.getComponent(id, "SpriteComponent")).getSprite();
                    PositionComponent pc = (PositionComponent) componentManager.getComponent(id, "PositionComponent");
                    sprite.setCenterX(pc.getX());
                    sprite.setCenterY(pc.getY());
                    sprite.draw(batch);
                }
            }
        }

        batch.end();
    }
}
