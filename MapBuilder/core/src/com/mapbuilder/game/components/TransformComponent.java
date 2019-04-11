package com.mapbuilder.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * The TransformComponent class is responsible of holding
 * the position, sclale and rotation of an entity that will use this component.
 */
public class TransformComponent implements Component {

    public Vector2 position;
    public Vector2 scale;
    public Vector2 rotation;

    public TransformComponent(Vector2 position, Vector2 scale, Vector2 rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }
}