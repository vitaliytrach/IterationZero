package com.mygdx.game.systems;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.EntityManager;
import com.mygdx.game.engine.SystemManager;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.EntityIDs;
import com.mygdx.game.utils.EntityRenderOrder;
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
                        TransformComponent tc = (TransformComponent) componentManager.getComponent(id, "TransformComponent");
                        sprite.setCenter(tc.getPosition().x , tc.getPosition().y);
                        sprite.draw(batch);
                    }
                }
            }
        }
    }
}
