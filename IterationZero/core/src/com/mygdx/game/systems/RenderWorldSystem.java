package com.mygdx.game.systems;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.data.TileData;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.EntityManager;
import com.mygdx.game.entities.World;
import com.mygdx.game.interfaces.ISystem;

public class RenderWorldSystem implements ISystem {

    private int id;
    private String type;
    private ComponentManager componentManager;
    private EntityManager entityManager;

    public RenderWorldSystem(int id) {
        this.id = id;
        type = "RenderWorldSystem";
        componentManager = ComponentManager.getInstance();
        entityManager = EntityManager.getInstance();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void render() {
        World world = (World) entityManager.getEntity(id);
        RenderComponent rc = (RenderComponent) componentManager.getComponent(id, "RenderComponent");
        SpriteBatch batch = rc.getSpriteBatch();

        TransformComponent playerPositionComp = (TransformComponent) componentManager.getComponent(0, "TransformComponent");
        float playerX = playerPositionComp.getPosition().x;
        float playerY = playerPositionComp.getPosition().y;
        for(int i = 0; i < world.getHeight(); i++) {
            for(int j = 0; j < world.getWidth(); j++) {
                TileData tile = world.getTile(j, i);
                Sprite sprite = tile.getTileSprite();
                sprite.setCenter(tile.getX(), tile.getY());
                if (tile.getX() <= playerX + 200 && tile.getX() >= playerX - 200)
                    sprite.draw(batch);
            }
        }
    }
}
