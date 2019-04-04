package com.mygdx.game.engine;

import com.mygdx.game.interfaces.IEntity;

import java.util.HashMap;

public class EntityManager {

    private static EntityManager instance;
    private HashMap<Integer, IEntity> entities;

    private EntityManager() {
        entities = new HashMap<Integer, IEntity>();
    }

    public static EntityManager getInstance() {
        if(instance == null) {
            instance = new EntityManager();
        }

        return instance;
    }

    public IEntity getEntity(int eid) {
        return entities.get(eid);
    }

    public void addEntity(IEntity e) {
        if(!entities.containsKey(e.getID())) {
            entities.put(e.getID(), e);
        }
    }

    public HashMap<Integer, IEntity> getEntites() {
        return entities;
    }
}