package com.mygdx.game.engine;

import com.mygdx.game.interfaces.ISystem;

import java.util.HashMap;

public class SystemManager {

    private static SystemManager instance;
    private HashMap<Integer, ISystem> systems;

    private SystemManager() {
        systems = new HashMap<Integer, ISystem>();
    }

    public static SystemManager getInstance() {
        if(instance == null) {
            instance = new SystemManager();
        }

        return instance;
    }

    public void addSystem(ISystem system) {
        if(!systems.containsKey(system.getID())) {
            systems.put(system.getID(), system);
        }
    }

    public HashMap<Integer, ISystem> getSystems() {
        return systems;
    }
}
