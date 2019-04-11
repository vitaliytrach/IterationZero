package com.mygdx.game.systems;

import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.EntityManager;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;

import java.util.HashMap;
import java.util.Map;

public class CollisionDetectionSystem implements ISystem {

    private int id;
    private String type;
    private ComponentManager cm;
    private EntityManager em;

    public CollisionDetectionSystem(int id) {
        this.id = id;
        type = "CollisionDetectionSystem";
        cm = ComponentManager.getInstance();
        em = EntityManager.getInstance();
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

        boolean[] directions = {false, false, false, false};
        LocationComponent lc = (LocationComponent) cm.getComponent(id, "LocationComponent");
        EntityStateComponent esc = (EntityStateComponent) cm.getComponent(id, "EntityStateComponent");

        HashMap<Integer, IEntity> entities = em.getEntityTypes("NPCEntity");

        for(Map.Entry<Integer, IEntity> e : entities.entrySet()) {

            LocationComponent elc = (LocationComponent) cm.getComponent(e.getKey(), "LocationComponent");

            if(lc.getX() == elc.getX()) {
                if(lc.getY() -1 == elc.getY()) {
                    directions[EntityStateComponent.UP] = true;
                } else if(lc.getY() + 1 == elc.getY()) {
                    directions[EntityStateComponent.DOWN] = true;
                }
            } else if(lc.getY() == elc.getY()) {
                if(lc.getX() + 1 == elc.getX()) {
                    directions[EntityStateComponent.RIGHT] = true;
                } else if(lc.getX() - 1 == elc.getX()) {
                    directions[EntityStateComponent.LEFT] = true;
                }
            }
        }

        esc.setAvailableDirections(directions);
    }
}
