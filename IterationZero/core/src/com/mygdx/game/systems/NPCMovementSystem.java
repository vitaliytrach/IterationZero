package com.mygdx.game.systems;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.components.MapComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.data.Tile;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.DirectionAdapter;
import com.mygdx.game.utils.EntityIDs;

import java.util.Random;

public class NPCMovementSystem implements ISystem {

    private int id;
    private String type;
    private ComponentManager cm;
    private int moveP;
    private Random rand;
    private float moveX, moveY;
    private int direction, counter;

    public NPCMovementSystem(int id) {
        this.id = id;
        type = "NPCMovementSystem";
        moveP = 50;
        cm = ComponentManager.getInstance();
        rand = new Random();
        direction = 0;
        counter = 0;
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

        if(!esc.isMoving()) {
            int roll = rand.nextInt(100);

            if(roll <= moveP) {
                direction = rand.nextInt(4);

                if(esc.hasNeighbor(direction)) {
                    moveP = moveP + (moveP >> 1);
                }  else {
                    moveP = 50;
                    String dir = DirectionAdapter.intToStringDirection(direction);
                    MapComponent mc = (MapComponent) cm.getComponent(EntityIDs.WORLD_ID, "MapComponent");

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
            }
        }

        if(esc.isMoving()) {
            if(counter >= MovementSystem.TICKS_PER_BLOCK_MOVEMENT) {
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
