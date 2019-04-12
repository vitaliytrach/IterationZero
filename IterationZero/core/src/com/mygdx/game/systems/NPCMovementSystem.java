package com.mygdx.game.systems;

import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.DirectionAdapter;

import java.util.Random;

public class NPCMovementSystem implements ISystem {

    private int id;
    private String type;
    private ComponentManager cm;
    private int moveP;
    private Random rand;

    public NPCMovementSystem(int id) {
        this.id = id;
        type = "NPCMovementSystem";
        moveP = 50;
        cm = ComponentManager.getInstance();
        rand = new Random();
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
        EntityStateComponent esc = (EntityStateComponent) cm.getComponent(id, "EntityComponentSystem");

        if(!esc.isMoving()) {
            int roll = rand.nextInt(100);

            if(roll <= moveP) {
                int dir = rand.nextInt(4);

                if(esc.hasNeighbor(dir)) {
                    moveP = moveP + (moveP >> 1);
                } else {
                    move(DirectionAdapter.intToStringDirection(dir));
                }
            }
        }
    }

    private void move(String direction) {
       // if(direction)

    }
}
