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

/**
 * The MovementSystem is responsible off moving the whole world
 * when a player wants to move. Essentially the player is always
 * centered, and everything else moves, giving the illusion that
 * the character is moving.
 */
public class MovementSystem implements ISystem {

    /**
     * Variable that states how fast the player can move 1 block.
     * For example, 32 ticks means that it takes the player 32 ticks to move 1 block.
     */
    public static final int TICKS_PER_BLOCK_MOVEMENT = 32;
    public static final int MOVEMENT_DIRECTIONS = 4;

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

        int direction = 0;
        /**
         * This next if statement checks if the character is NOT moving,
         * if he's not moving then it checks if any of the movement keys
         * are pressed, and updating all relevant information once they are pressed.
         * Such as: isMoving = true, player map x and y position gets updated,
         *          and the moveX and moveY variables are set to the proper
         *          speed and direction.
         */
        if(!esc.isMoving()) {
            if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
                esc.changeDirection("up");
                if((lc.getY() - 1) < 0) { return; }
                if(esc.hasNeighbor(EntityStateComponent.UP)) { return; }
                lc.setY(lc.getY() - 1);
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY) * -1;
                esc.setMoveStatus(true);
            } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                esc.changeDirection("down");
                if((lc.getY() + 1) > mc.getHeight() - 1) { return; }
                if(esc.hasNeighbor(EntityStateComponent.DOWN)) { return; }
                lc.setY(lc.getY() + 1);
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY);
                esc.setMoveStatus(true);
            } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                esc.changeDirection("left");
                if((lc.getX() - 1) < 0) { return; }
                if(esc.hasNeighbor(EntityStateComponent.LEFT)) { return; }
                lc.setX(lc.getX() - 1);
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY) * -1;
                esc.setMoveStatus(true);
            } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                esc.changeDirection("right");
                if((lc.getX() + 1) > mc.getWidth() - 1) { return; }
                if(esc.hasNeighbor(EntityStateComponent.RIGHT)) { return; }
                lc.setX(lc.getX() + 1);
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

    /**
     * Moving all the world tiles
     */
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

    /**
     * Moving all the static world entities, like tress, rocks etc...
     */
    private void updateStaticEntities() {
        for(Map.Entry<Integer, IEntity> e : em.getEntites().entrySet()) {
            TransformComponent tc = (TransformComponent) cm.getComponent(e.getKey(), "TransformComponent");

            if(e.getValue().isStatic()) {
                tc.setPostion(new Vector2(tc.getPosition().x + moveX, tc.getPosition().y + moveY));
            } else if(e.getValue().getType() == "GoatEntity"){
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
