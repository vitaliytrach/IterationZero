package com.mygdx.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.interfaces.IBuilder;
import com.mygdx.game.interfaces.IComponent;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.EntityIDs;
import com.mygdx.game.utils.SystemUpdateOrder;
import java.util.Map;

/**
 * The ECSEngine is the core of our game, it's responsibilities
 * include keeping track off all the different Managers, and
 * calling all the render() method's of all the systems.
 */
public class ECSEngine {

    private ComponentManager componentManager;
    private SystemManager systemManager;
    private EntityManager entityManager;
    private SpriteBatch batch;
    private Camera camera;

    public ECSEngine(SpriteBatch batch, Camera camera) {
        componentManager = ComponentManager.getInstance();
        systemManager = SystemManager.getInstance();
        entityManager = EntityManager.getInstance();
        this.camera = camera;
        this.batch = batch;
    }

    public void addEntity(IBuilder builder) {
        entityManager.addEntity(builder.getEntity());

        for(IComponent c : builder.getComponentList()) {
            componentManager.addComponent(c);
        }

        for(ISystem s : builder.getSystemList()) {
            systemManager.addSystem(s);
        }
    }

    public void render() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        //TransformComponent playerTransform = (TransformComponent) componentManager.getComponent(EntityIDs.PLAYER_ID, "TransformComponent");
        //camera.position.set(playerTransform.getPosition().x, playerTransform.getPosition().y, 0);


        for (String sType : SystemUpdateOrder.getUpdateOrder()) {
            if(systemManager.hasSystem(sType)) {
                for (Map.Entry<Integer, ISystem> system : systemManager.getSystemIds(sType).entrySet()) {
                    system.getValue().render();
                }
            }
        }

        camera.update();
        batch.end();
    }
}
