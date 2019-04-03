package com.mygdx.game.engine;

import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;

import java.util.ArrayList;
import java.util.Map;

public class ECSEngine {

    private static ECSEngine instance;
    private ComponentManager componentManager;
    private SystemManager systemManager;
    private ArrayList<IEntity> entities;

    private ECSEngine() {
        componentManager = ComponentManager.getInstance();
        systemManager = SystemManager.getInstance();
        entities = new ArrayList<IEntity>();
    }

    private static ECSEngine getInstance() {
        if(instance == null) {
            instance = new ECSEngine();
        }

        return instance;
    }

    public void addEntity(IEntity e, ArrayList<IComponent> cl, ArrayList<ISystem> sl) {
        entities.add(e);

        for(IComponent c : cl) {
            componentManager.addComponent(c);
        }

        for(ISystem s : sl) {
            systemManager.addSystem(s);
        }
    }

    void render() {
        for(Map.Entry<Integer,ISystem> s : systemManager.getSystems().entrySet()) {
            s.getValue().render();
        }
    }
}
