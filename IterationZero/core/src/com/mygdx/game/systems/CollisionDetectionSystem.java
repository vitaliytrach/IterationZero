package com.mygdx.game.systems;

import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.EntityManager;
import com.mygdx.game.engine.WallManager;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;

import java.util.HashMap;
import java.util.Map;

public class CollisionDetectionSystem implements ISystem {

    private int id;
    private String type;
    private WallManager wm;
    private ComponentManager cm;
    private EntityManager em;

    public CollisionDetectionSystem(int id) {
        this.id = id;
        type = "CollisionDetectionSystem";
        cm = ComponentManager.getInstance();
        em = EntityManager.getInstance();
        wm = WallManager.getInstance();
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

        boolean[] directions = {true, true, true, true};
        LocationComponent lc = (LocationComponent) cm.getComponent(id, "LocationComponent");
        EntityStateComponent esc = (EntityStateComponent) cm.getComponent(id, "EntityStateComponent");
        int x = lc.getX();
        int y = lc.getY();

        // Check up
        if (wm.isWall(lc.getMap(), x, y - 1)) {
            directions[EntityStateComponent.UP] = false;
        }

        // Check down
        if (wm.isWall(lc.getMap(), x, y + 1)) {
            directions[EntityStateComponent.DOWN] = false;
        }

        // Check right
        if (wm.isWall(lc.getMap(), x + 1, y)) {
            directions[EntityStateComponent.RIGHT] = false;
        }

        // Check left
        if (wm.isWall(lc.getMap(), x - 1, y)) {
            directions[EntityStateComponent.LEFT] = false;
        }

        esc.setAvailableDirections(directions);
    }
}
