package com.mapbuilder.game.engine;

import com.badlogic.ashley.core.ComponentMapper;
import com.mapbuilder.game.components.RenderComponent;
import com.mapbuilder.game.components.TransformComponent;

public class ComponentManger {

    private static ComponentManger instance;

    public static final ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<RenderComponent> render = ComponentMapper.getFor(RenderComponent.class);

    public ComponentManger() {

    }

    public static ComponentManger getInstance() {
        if(instance == null) {
            instance = new ComponentManger();
        }
        return instance;
    }
}
