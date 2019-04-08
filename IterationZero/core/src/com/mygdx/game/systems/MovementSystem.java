package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.components.MapComponent;
import com.mygdx.game.data.Tile;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.EntityManager;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.EntityIDs;

public class MovementSystem implements ISystem {

    private static final int TICKS_PER_BLOCK_MOVEMENT = 32;

    private int id;
    private String type;
    private ComponentManager cm;
    private EntityManager em;
    private String direction;
    private float moveX, moveY;
    private boolean isMoving;
    private int counter;

    public MovementSystem(int id) {
        this.id = id;
        type = "MovementSystem";
        direction = "idle";
        cm = ComponentManager.getInstance();
        em = EntityManager.getInstance();
        moveX = (Tile.DEFAULT_TILE_WIDTH / 2) / TICKS_PER_BLOCK_MOVEMENT;
        moveY = (Tile.DEFAULT_TILE_HEIGHT /2) / TICKS_PER_BLOCK_MOVEMENT;
        isMoving = false;
        counter = 0;
    }

    @Override
    public void render() {

        LocationComponent lc = (LocationComponent) cm.getComponent(EntityIDs.PLAYER_ID, "LocationComponent");
        MapComponent mc = (MapComponent) cm.getComponent(EntityIDs.WORLD_ID, "MapComponent");

        if(direction == "idle") {
            if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
                if((lc.getY() - 1) < 0) { return; }
                lc.setY(lc.getY() - 1);
                direction = "up";
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY) * -1;
                isMoving = true;
            } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                if((lc.getY() + 1) > mc.getHeight() - 1) { return; }
                lc.setY(lc.getY() + 1);
                direction = "down";
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY);
                isMoving = true;
            } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if((lc.getX() - 1) < 0) { return; }
                lc.setX(lc.getX() - 1);
                direction = "left";
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY) * -1;
                isMoving = true;
            } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if((lc.getX() + 1) > mc.getWidth() - 1) { return; }
                lc.setX(lc.getX() + 1);
                direction = "right";
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY);
                isMoving = true;
            }
        }

        if(isMoving) {
            if(counter >= TICKS_PER_BLOCK_MOVEMENT) {
                counter = 0;
                direction = "idle";
                isMoving = false;
                return;
            }

            moveWorld();
        }
    }

    private void moveWorld() {
        MapComponent mc = (MapComponent) cm.getComponent(EntityIDs.WORLD_ID, "MapComponent");

        for(int i = 0; i < mc.getHeight(); i++) {
            for(int j = 0; j < mc.getWidth(); j++) {
                Tile tile = mc.getTile(i, j);
                tile.setX(tile.getX() + moveX);
                tile.setY(tile.getY() + moveY);
            }
        }
        counter++;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }
}
