package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.EntityManager;
import com.mygdx.game.entities.Tile;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.EntityIDs;

public class MovePlayerSystem implements ISystem {

    private static final int TICKS_PER_BLOCK = 16;

    private int id;
    private String type;
    private ComponentManager cm;
    private EntityManager em;
    private boolean isMoving;
    private int counter;
    private float moveX, moveY;

    /**
     * Player state keeps track of what the player is currently doing, there are 5:
     * idle = not doing anything
     * up = moving up
     * down = moving down
     * left = moving left
     * right = moving right
     */
    private String playerState;

    public MovePlayerSystem(int id) {
        this.id = id;
        type = "MovePlayerSystem";
        cm = ComponentManager.getInstance();
        em = EntityManager.getInstance();
        playerState = "idle";
        isMoving = false;
        moveX = (Tile.DEFAULT_TILE_WIDTH / 2) / TICKS_PER_BLOCK;
        moveY = (Tile.DEFAULT_TILE_HEIGHT /2) / TICKS_PER_BLOCK;
        counter = 0;
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

        LocationComponent lc = (LocationComponent) cm.getComponent(id, "LocationComponent");

        if(playerState == "idle") {
            if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
                playerState = "up";
                lc.setY(lc.getY() + 1);
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY);
                isMoving = true;
            } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                lc.setY(lc.getY() - 1);
                playerState = "down";
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY) * -1;
                isMoving = true;
            } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                lc.setY(lc.getX() - 1);
                playerState = "left";
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY);
                isMoving = true;
            } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                lc.setY(lc.getX() + 1);
                playerState = "right";
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY) * -1;
                isMoving = true;
            }
        }

        if(isMoving) {
            if(counter >= TICKS_PER_BLOCK) {
                counter = 0;
                playerState = "idle";
                isMoving = false;
                return;
            }

            movePlayer();
        }
    }

    private void movePlayer() {
        TransformComponent tc = (TransformComponent) cm.getComponent(EntityIDs.PLAYER_ID, "TransformComponent");
        Vector2 playerPos = tc.getPosition();
        tc.setPostion(new Vector2(playerPos.x + moveX, playerPos.y + moveY));
        counter++;
    }
}
