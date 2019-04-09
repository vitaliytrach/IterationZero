package com.mapbuilder.game.components;

import com.badlogic.gdx.math.Vector2;
import com.mapbuilder.game.interfaces.IComponent;

/**
 * The TransformComponent class is responsible of holding
 * the position, sclale and rotation of an entity that will use this component.
 */
public class TransformComponent implements IComponent {

    private int id;
    private String type;
    private Vector2 position;
    private Vector2 scale;
    private Vector2 rotation;

    public TransformComponent(int id, Vector2 position, Vector2 scale, Vector2 rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
        this.id = id;
        type = "TransformComponent";
    }

    //GET
    public Vector2 getPosition() {
        return position; // change this later to return a copy and not the reference
    }

    //SET
    public void setPostion(Vector2 position){
        this.position = position;
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