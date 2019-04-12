package com.mapbuilder.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mapbuilder.game.components.RenderComponent;
import com.mapbuilder.game.components.TransformComponent;
import com.mapbuilder.game.engine.GameEngine;

import javax.xml.crypto.dsig.Transform;

public class RenderSystem extends EntitySystem {

    private ImmutableArray<Entity> renderEntities;
    private Engine engine = GameEngine.getInstance().engine;
    private SpriteBatch batch;

    public RenderSystem(){
        renderEntities = engine.getEntitiesFor(Family.all(RenderComponent.class).get());
        batch = new SpriteBatch();
    }


    @Override
    public void update(float deltaTime){

        for (int i = 0; i < renderEntities.size(); i++){

            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
            batch.begin();
            Entity entity = renderEntities.get(i);
            Vector2 position = entity.getComponent(TransformComponent.class).position;
            Sprite sprite = entity.getComponent(RenderComponent.class).sprite;
            sprite.setCenter(position.x, position.y);
            sprite.draw(batch);
            batch.end();
        }
    }


}
