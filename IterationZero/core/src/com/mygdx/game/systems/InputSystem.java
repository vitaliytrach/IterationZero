package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.data.TileData;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.entities.World;
import com.mygdx.game.interfaces.ISystem;

public class InputSystem implements ISystem {

    private int id;
    private String type;
    private World world;

    public InputSystem(int id, World world) {
        this.id = id;
        this.world = world;
        type = "InputSystem";
    }

    @Override
    public void render() {

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
