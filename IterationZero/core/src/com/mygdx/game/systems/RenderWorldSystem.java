package com.mygdx.game.systems;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.IterationZero;
import com.mygdx.game.components.RenderComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.data.TileData;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.EntityManager;
import com.mygdx.game.entities.Tile;
import com.mygdx.game.entities.World;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.EntityIDs;

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

        TransformComponent playerPositionComp = (TransformComponent) componentManager.getComponent(EntityIDs.PLAYER_ID, "TransformComponent");

        // Setting the render distance boundaries
        float rdStartX = (playerPositionComp.getPosition().x - IterationZero.SCREEN_WIDTH / 2) - Tile.DEFAULT_TILE_WIDTH * 2;
        float rdEndX = (playerPositionComp.getPosition().x + IterationZero.SCREEN_HEIGHT / 2) + Tile.DEFAULT_TILE_WIDTH * 2;
        float rdStartY = (playerPositionComp.getPosition().y - IterationZero.SCREEN_HEIGHT / 2) - Tile.DEFAULT_TILE_HEIGHT * 2;
        float rdEndY = (playerPositionComp.getPosition().y + IterationZero.SCREEN_HEIGHT / 2) + Tile.DEFAULT_TILE_HEIGHT * 2;

        for(int i = 0; i < world.getHeight(); i++) {
            for(int j = 0; j < world.getWidth(); j++) {
                TileData tile = world.getTile(j, i);
                Sprite sprite = tile.getTileSprite();
                sprite.setCenter(tile.getX(), tile.getY());

                // Drawing sprites that are within the render distance
                if (tile.getX() >= rdStartX && tile.getX() <= rdEndX &&
                        tile.getY() >= rdStartY && tile.getY() <= rdEndY) {
                    sprite.draw(batch);
                }
            }
        }
    }
}
