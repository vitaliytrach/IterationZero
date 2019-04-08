package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.data.TileData;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.entities.World;
import com.mygdx.game.entities.Player;
import com.mygdx.game.interfaces.ISystem;
import com.badlogic.gdx.math.Vector2;

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
            tc.setPostion(new Vector2(playerPosition.x - 3, playerPosition.y + 3));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            tc.setPostion(new Vector2(playerPosition.x + 3, playerPosition.y - 3));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            tc.setPostion(new Vector2(playerPosition.x + (float) (3 * Math.sqrt(3)), playerPosition.y + 3));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            tc.setPostion(new Vector2(playerPosition.x - 3, playerPosition.y - 3));
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
