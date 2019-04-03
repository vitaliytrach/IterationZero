package com.mygdx.game.engine;

import com.mygdx.game.interfaces.IComponent;

import java.util.HashMap;

public class ComponentManager {

    private static ComponentManager instance;

    // HashMap of all Components that a specific id is using.
    // Example (Player id = 1), so the Key is "1", and the value is
    // a HashMap of all the Components the player is composed of.
    private HashMap<Integer, HashMap<String, IComponent>> componentIds;

    // HashMap of all Id's that use a specific component.
    // Example ("SpriteComponent"), the key is "SpriteComponent"
    // and the value is a HashMap of all the ID's that have a
    // Sprite component, such as (id = 1 (player), id = 2 (rock), id = 3 (tree))..
    // All of those have SpriteComponents
    private HashMap<String, HashMap<Integer, IComponent>> componentTypes;


    private ComponentManager() {
        componentIds = new HashMap<Integer, HashMap<String, IComponent>>();
        componentTypes = new HashMap<String, HashMap<Integer, IComponent>>();
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

        if(component == null) {
            return;
        }

        int cid = component.getID();
        String ctype = component.getType();

        if(!componentIds.containsKey(cid)) {
            componentIds.put(cid, new HashMap<String, IComponent>());
        }

        if(!componentIds.get(cid).containsKey(ctype)) {
            componentIds.get(cid).put(ctype, component);
        }

        if(!componentTypes.containsKey(ctype)) {
            componentTypes.put(ctype, new HashMap<Integer, IComponent>());
        }

        if(!componentTypes.get(ctype).containsKey(cid)) {
            componentTypes.get(ctype).put(cid, component);
        }
    }

    public IComponent getComponent(int id, String type) {
        return componentIds.get(id).get(type);
    }
}
