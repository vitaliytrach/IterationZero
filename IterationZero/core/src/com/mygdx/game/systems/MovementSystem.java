package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.components.MapComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.data.Tile;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.EntityManager;
import com.mygdx.game.interfaces.IEntity;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.EntityIDs;

import java.util.Map;

public class MovementSystem implements ISystem {

    private static final int TICKS_PER_BLOCK_MOVEMENT = 32;

    private int id;
    private String type;
    private ComponentManager cm;
    private EntityManager em;
    private float moveX, moveY;
    private int counter;

    public MovementSystem(int id) {
        this.id = id;
        type = "MovementSystem";
        cm = ComponentManager.getInstance();
        em = EntityManager.getInstance();
        moveX = (Tile.DEFAULT_TILE_WIDTH / 2) / TICKS_PER_BLOCK_MOVEMENT;
        moveY = (Tile.DEFAULT_TILE_HEIGHT /2) / TICKS_PER_BLOCK_MOVEMENT;
        counter = 0;
    }

    @Override
    public void render() {

        LocationComponent lc = (LocationComponent) cm.getComponent(EntityIDs.PLAYER_ID, "LocationComponent");
        MapComponent mc = (MapComponent) cm.getComponent(EntityIDs.WORLD_ID, "MapComponent");
        EntityStateComponent esc = (EntityStateComponent) cm.getComponent(EntityIDs.PLAYER_ID, "EntityStateComponent");
        String direction = esc.getDirection();

        if(!esc.isMoving()) {
            if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
                if((lc.getY() - 1) < 0) { return; }
                lc.setY(lc.getY() - 1);
                esc.changeDirection("up");
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY) * -1;
                esc.setMoveStatus(true);
            } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                if((lc.getY() + 1) > mc.getHeight() - 1) { return; }
                lc.setY(lc.getY() + 1);
                esc.changeDirection("down");
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY);
                esc.setMoveStatus(true);
            } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if((lc.getX() - 1) < 0) { return; }
                lc.setX(lc.getX() - 1);
                esc.changeDirection("left");
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY) * -1;
                esc.setMoveStatus(true);
            } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if((lc.getX() + 1) > mc.getWidth() - 1) { return; }
                lc.setX(lc.getX() + 1);
                esc.changeDirection("right");
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY);
                esc.setMoveStatus(true);
            }
        }

        if(esc.isMoving()) {
            if(counter >= TICKS_PER_BLOCK_MOVEMENT) {
                counter = 0;
                esc.setMoveStatus(false);
                return;
            }

            moveWorld();
            updateStaticEntities();
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

    private void updateStaticEntities() {
        for(Map.Entry<Integer, IEntity> e : em.getEntites().entrySet()) {
            if(e.getValue().isStatic()) {
                TransformComponent tc = (TransformComponent) cm.getComponent(e.getKey(), "TransformComponent");
                tc.setPostion(new Vector2(tc.getPosition().x + moveX, tc.getPosition().y + moveY));
            }
        }
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
