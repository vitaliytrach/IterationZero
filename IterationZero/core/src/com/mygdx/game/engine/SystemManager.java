package com.mygdx.game.engine;

import com.mygdx.game.interfaces.ISystem;

import java.util.HashMap;

/**
 * The SystemManager is responsible for keeping
 * track off all the Systems that are in the game.
 * It does this in 2 ways:
 * 1. Keeps a HashMap of all systems related to a specific ID
 * 2. Keeps a HashMap of all id's that use a specific system
 */
public class SystemManager {

    private static SystemManager instance;

    private HashMap<Integer, HashMap<String, ISystem>> systemIds;
    private HashMap<String, HashMap<Integer, ISystem>> systemTypes;

    private SystemManager() {
        systemIds = new HashMap<Integer, HashMap<String, ISystem>>();
        systemTypes = new HashMap<String, HashMap<Integer, ISystem>>();
    }

    public static SystemManager getInstance() {
        if(instance == null) {
            instance = new SystemManager();
        }

        return instance;
    }

    public void addSystem(ISystem system) {

        if(system == null) {
            return;
        }

        int sid = system.getID();
        String stype = system.getType();

        if(!systemIds.containsKey(sid)) {
            systemIds.put(sid, new HashMap<String, ISystem>());
        }

        if(!systemIds.get(sid).containsKey(stype)) {
            systemIds.get(sid).put(stype, system);
        }

        if(!systemTypes.containsKey(stype)) {
            systemTypes.put(stype, new HashMap<Integer, ISystem>());
        }

        if(!systemTypes.get(stype).containsKey(sid)) {
            systemTypes.get(stype).put(sid, system);
        }
    }

    public ISystem getSystem(int sid, String stype) {
        return systemIds.get(sid).get(stype);
    }

    public HashMap<Integer, ISystem> getSystemIds(String stype) {
        return systemTypes.get(stype);
    }
}
