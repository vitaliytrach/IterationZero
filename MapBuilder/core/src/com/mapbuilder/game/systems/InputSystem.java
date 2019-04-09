package com.mapbuilder.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mapbuilder.game.components.TransformComponent;
import com.mapbuilder.game.engine.ComponentManager;
import com.mapbuilder.game.entities.Player;
import com.mapbuilder.game.interfaces.ISystem;

public class InputSystem implements ISystem {

    private int id;
    private String type;
    //private World world;
    private Player player;
    private ComponentManager cm;

    public InputSystem(int id, Player player) {
        this.id = id;
        this.player = player;
        type = "InputSystem";
        cm  = ComponentManager.getInstance();
    }

    @Override
    public void render() {

        TransformComponent tc = (TransformComponent) cm.getComponent(player.getID(), "TransformComponent");
        Vector2 playerPosition = tc.getPosition();

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            tc.setPostion(new Vector2(playerPosition.x + 5, playerPosition.y));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            tc.setPostion(new Vector2(playerPosition.x - 5, playerPosition.y));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            tc.setPostion(new Vector2(playerPosition.x, playerPosition.y + 5));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            tc.setPostion(new Vector2(playerPosition.x, playerPosition.y - 5));
        }

        /*
        for(int i = 0; i < world.getHeight(); i++){
            for(int j = 0; j < world.getWidth(); j++) {

                TileData tile = world.getTile(j, i);

                if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    tile.setX(tile.getX() + 5);
                }

                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    tile.setX(tile.getX() - 5);
                }

                if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    tile.setY(tile.getY() - 5);
                }

                if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    tile.setY(tile.getY() + 5);
                }
            }
        }
        */
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
