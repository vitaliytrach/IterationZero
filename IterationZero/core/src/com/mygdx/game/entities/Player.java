package com.mygdx.game.entities;

import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.interfaces.IEntity;

import javax.swing.text.Position;


public class Player implements IEntity {

    private int id;
    private String type;
    //private TransformComponent transform;

    public Player(int id) {
        this.id = id;
        type = "PlayerEntity";
        //transform = new TransformComponent(id, new Vector2(0,0), new Vector2(0,0), new Vector2(0,0));
    }

    //public TransformComponent getTransform() {
    //    return transform; // needs to return a copy and not the reference
    //}

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }
}
