package com.mapbuilder.game.systems;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mapbuilder.game.components.RenderComponent;
import com.mapbuilder.game.components.SpriteComponent;
import com.mapbuilder.game.components.TransformComponent;
import com.mapbuilder.game.engine.ComponentManager;
import com.mapbuilder.game.engine.EntityManager;
import com.mapbuilder.game.engine.SystemManager;
import com.mapbuilder.game.interfaces.IEntity;
import com.mapbuilder.game.interfaces.ISystem;
import com.mapbuilder.game.utils.EntityRenderOrder;

import java.util.HashMap;
import java.util.Map;

public class RenderSystem implements ISystem {

    private int id;
    private String type;
    private ComponentManager componentManager;
    private EntityManager entityManager;
    private SystemManager systemManager;

    public RenderSystem(int id) {
        this.id = id;
        type = "RenderSystem";
        componentManager = ComponentManager.getInstance();
        entityManager = EntityManager.getInstance();
        systemManager = SystemManager.getInstance();
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

        if(entities != null) {

            for(String s : EntityRenderOrder.getRenderOrder()) {

                HashMap<Integer, IEntity> e1 = entityManager.getEntityTypes(s);

                for(Map.Entry<Integer,IEntity> e : e1.entrySet()) {

                    int id = e.getValue().getID();

                    // Check to make sure only the Entities with a RenderSystem execute the follow code
                    if(systemManager.hasSystem(id, "RenderSystem")){
                        Sprite sprite = ((SpriteComponent) componentManager.getComponent(id, "SpriteComponent")).getSprite();
                        //PositionComponent pc = (PositionComponent) componentManager.getComponent(id, "PositionComponent");
                        //sprite.setCenter(pc.getX(), pc.getY());
                        TransformComponent tc = (TransformComponent) componentManager.getComponent(id, "TransformComponent");
                        sprite.setCenter(tc.getPosition().x, tc.getPosition().y);
                        sprite.draw(batch);
                    }
                }
            }
        }
    }
}