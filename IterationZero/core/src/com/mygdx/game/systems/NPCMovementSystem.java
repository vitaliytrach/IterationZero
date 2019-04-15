package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.components.MapComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.data.Tile;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.BreadthFirstSearch;
import com.mygdx.game.utils.MapUtils;

import java.util.*;

public class NPCMovementSystem implements ISystem {

    private int id;
    private String type;
    private ComponentManager cm;
    private Random rand;
    private float moveX, moveY;
    private int counter;
    private boolean shouldFindPath;
    private ArrayList<String> tempPath;
    private float deltaTime;
    private int targetGoal;

    public NPCMovementSystem(int id) {
        this.id = id;
        type = "NPCMovementSystem";
        cm = ComponentManager.getInstance();
        rand = new Random();
        counter = 0;
        targetGoal = 3;
        shouldFindPath = true;
        deltaTime = 0;
        tempPath = new ArrayList<String>();
        moveX = (Tile.DEFAULT_TILE_WIDTH / 2) / MovementSystem.TICKS_PER_BLOCK_MOVEMENT;
        moveY = (Tile.DEFAULT_TILE_HEIGHT / 2)/ MovementSystem.TICKS_PER_BLOCK_MOVEMENT;
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
        EntityStateComponent esc = (EntityStateComponent) cm.getComponent(id, "EntityStateComponent");
        LocationComponent lc = (LocationComponent) cm.getComponent(id, "LocationComponent");
        MapComponent mc = (MapComponent) cm.getComponent(lc.getMap(), "MapComponent");

        deltaTime += Gdx.graphics.getDeltaTime();

        if(!esc.isMoving()) {

            if(shouldFindPath) {
                tempPath = BreadthFirstSearch.bfsPath(id, lc.getX(), lc.getY());

                shouldFindPath = false;
            }

            if(deltaTime < targetGoal) {
                return;
            }

            targetGoal = rand.nextInt(5);
            deltaTime = 0;

            if(tempPath.size() <= 1) {
                shouldFindPath = true;
                return;
            }

            String d1 = tempPath.remove(0);
            String dir = MapUtils.nextDirection(d1, tempPath.get(0));

            if(dir == "up") {
                if((lc.getY() - 1) < 0) { return; }
                lc.setY(lc.getY() - 1);
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY);
                esc.setMoveStatus(true);
            } else if(dir == "down") {
                if((lc.getY() + 1) > mc.getHeight() - 1) { return; }
                lc.setY(lc.getY() + 1);
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY) * -1;
                esc.setMoveStatus(true);
            } else if(dir == "right") {
                if((lc.getX() + 1) > mc.getWidth()) { return; }
                lc.setX(lc.getX() + 1);
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY) * -1;
                esc.setMoveStatus(true);
            } else if(dir == "left") {
                if((lc.getX() - 1) < 0) { return; }
                lc.setX(lc.getX() - 1);
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY);
                esc.setMoveStatus(true);
            }
        }

        if(esc.isMoving()) {
            if(counter >= MovementSystem.TICKS_PER_BLOCK_MOVEMENT) {
                if(tempPath.size() <= 1) {
                    shouldFindPath = true;
                }

                counter = 0;
                moveX = (Tile.DEFAULT_TILE_WIDTH / 2) / MovementSystem.TICKS_PER_BLOCK_MOVEMENT;
                moveY = (Tile.DEFAULT_TILE_HEIGHT / 2)/ MovementSystem.TICKS_PER_BLOCK_MOVEMENT;
                esc.setMoveStatus(false);
                return;
            }

            move();
        }
    }

    private void move() {
        TransformComponent tc = (TransformComponent) cm.getComponent(id, "TransformComponent");
        tc.setPostion(new Vector2(tc.getPosition().x + moveX, tc.getPosition().y + moveY));
        counter++;
    }
}
