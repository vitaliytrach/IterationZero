package com.mygdx.game.engine;

import com.mygdx.game.interfaces.IComponent;

import java.util.HashMap;

public class ComponentManager {

    private static ComponentManager instance;
    private HashMap<Integer, IComponent> components;

    private ComponentManager() {
        components = new HashMap<Integer, IComponent>();
    }

    // Singleton so that all classes uses ComponentManager have
    // the same components.
    public static ComponentManager getInstance() {
        if(instance == null) {
            instance = new ComponentManager();
        }

        return instance;
    }

    public void addComponent(IComponent component) {
        if(!components.containsKey(component.getID())) {
            components.put(component.getID(), component);
        }
    }

    public HashMap<Integer, IComponent> getComponents() {
        return components;
    }
}
