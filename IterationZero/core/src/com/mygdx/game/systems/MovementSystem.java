package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.data.TileData;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.EntityManager;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.Tile;
import com.mygdx.game.entities.World;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.EntityIDs;

public class MovementSystem implements ISystem {

    private static final int TICKS_PER_BLOCK_MOVEMENT = 32;

    private int id;
    private String type;
    private World world = null;
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

        if(world == null) {
            world = (World) em.getEntity(EntityIDs.WORLD_ID);
        }

        LocationComponent lc = (LocationComponent) cm.getComponent(EntityIDs.PLAYER_ID, "LocationComponent");

        if(direction == "idle") {
            if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
                lc.setY(lc.getY() + 1);
                direction = "up";
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY) * -1;
                isMoving = true;
            } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                lc.setY(lc.getY() - 1);
                direction = "down";
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY);
                isMoving = true;
            } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                lc.setX(lc.getX() - 1);
                direction = "left";
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY) * -1;
                isMoving = true;
            } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
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
        for(int i = 0; i < world.getHeight(); i++) {
            for(int j = 0; j < world.getWidth(); j++) {
                TileData tile = world.getTile(i, j);
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
