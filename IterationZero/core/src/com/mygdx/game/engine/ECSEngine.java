package com.mygdx.game.engine;

import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.SystemUpdateOrder;

import java.util.ArrayList;
import java.util.Map;

public class ECSEngine {

    private static ECSEngine instance;
    private ComponentManager componentManager;
    private SystemManager systemManager;
    private EntityManager entityManager;
    private ArrayList<IEntity> entities;

    private ECSEngine() {
        componentManager = ComponentManager.getInstance();
        systemManager = SystemManager.getInstance();
        entityManager = EntityManager.getInstance();
        entities = new ArrayList<IEntity>();
    }

    public static ECSEngine getInstance() {
        if (instance == null) {
            instance = new ECSEngine();
        }

        return instance;
    }

    public void addEntity(IEntity e, ArrayList<IComponent> cl, ArrayList<ISystem> sl) {
        entityManager.addEntity(e);

        for (IComponent c : cl) {
            componentManager.addComponent(c);
        }

        for (ISystem s : sl) {
            systemManager.addSystem(s);
        }
    }

    public void render() {

        for (String sType : SystemUpdateOrder.getUpdateOrder()) {
            for (Map.Entry<Integer, ISystem> system : systemManager.getSystemIds(sType).entrySet()) {
                system.getValue().render();
            }
        }
    }
}
