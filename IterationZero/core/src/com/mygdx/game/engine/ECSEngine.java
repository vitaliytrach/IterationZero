package com.mygdx.game.engine;

import com.mygdx.game.interfaces.IBuilder;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.SystemUpdateOrder;

import java.util.ArrayList;
import java.util.Map;

/**
 * The ECSEngine is the core of our game, it's responsibilities
 * include keeping track off all the different Managers, and
 * calling all the render() method's of all the systems.
 */
public class ECSEngine {

    private static ECSEngine instance;
    private ComponentManager componentManager;
    private SystemManager systemManager;
    private EntityManager entityManager;

    private ECSEngine() {
        componentManager = ComponentManager.getInstance();
        systemManager = SystemManager.getInstance();
        entityManager = EntityManager.getInstance();
    }

    public static ECSEngine getInstance() {
        if (instance == null) {
            instance = new ECSEngine();
        }

        return instance;
    }

    public void addEntity(IBuilder builder) {
        entityManager.addEntity(builder.getEntity());

        for(IComponent c : builder.getComponentList()) {
            componentManager.addComponent(c);
        }

        for(ISystem s : builder.getSystemList()) {
            systemManager.addSystem(s);
        }
    }

    public void render() {
        for (String sType : SystemUpdateOrder.getUpdateOrder()) {
            if(systemManager.hasSystem(sType)) {
                for (Map.Entry<Integer, ISystem> system : systemManager.getSystemIds(sType).entrySet()) {
                    system.getValue().render();
                }
            }
        }
    }
}
