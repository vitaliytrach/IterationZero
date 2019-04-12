package com.mygdx.game.systems;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.data.Tile;
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

        if(!esc.isMoving()) {
            int roll = rand.nextInt(100);

            if(roll <= moveP) {
                direction = rand.nextInt(4);

                if(!esc.hasNeighbor(direction)) {
                    moveP = moveP + (moveP >> 1);
                }  else {
                    esc.setMoveStatus(true);
                    moveP = 0;
                    String dir = DirectionAdapter.intToStringDirection(direction);
                    LocationComponent lc = (LocationComponent) cm.getComponent(id, "LocationComponent");

                    if(dir == "up") {
                        lc.setY(lc.getY() - 1);
                        moveX = Math.abs(moveX) * -1;
                        moveY = Math.abs(moveY) * -1;
                    } else if(dir == "down") {
                        lc.setY(lc.getY() + 1);
                        moveX = Math.abs(moveX);
                        moveY = Math.abs(moveY);
                    } else if(dir == "right") {
                        lc.setX(lc.getX() + 1);
                        moveX = Math.abs(moveX);
                        moveY = Math.abs(moveY) * -1;
                    } else if(dir == "left") {
                        lc.setX(lc.getX() - 1);
                        moveX = Math.abs(moveX) * -1;
                        moveY = Math.abs(moveY);
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
