package com.mapbuilder.game.systems;

import com.badlogic.ashley.core.EntitySystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mapbuilder.game.engine.GameAssetManager;


public class GameMapRenderer extends EntitySystem {

    private TiledMap map;
    private OrthographicCamera mainCamera;
    private MapRenderer mapRenderer;


    public GameMapRenderer() {
        map = GameAssetManager.getInstance().assetManager.get("maps/FirstMap.tmx");
        mainCamera = new OrthographicCamera(800, 800);
        mapRenderer = new IsometricTiledMapRenderer(map);
        mapRenderer.setView(mainCamera);
        mainCamera.position.x = 3100;
        mainCamera.position.y = 400;

    }

    @Override
    public void update(float deltaTime){

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) { // or A
            mainCamera.position.x -= 100 * Gdx.graphics.getDeltaTime(); // if conditions are ok, move the camera back.
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            mainCamera.position.x += 100 * Gdx.graphics.getDeltaTime(); // if conditions are ok, move the camera to the front.
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) { // or A
            mainCamera.zoom -= .1 * Gdx.graphics.getDeltaTime(); ; // if conditions are ok, move the camera back.
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            mainCamera.zoom += .1 * Gdx.graphics.getDeltaTime();  // if conditions are ok, move the camera to the front.
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();  // if conditions are ok, move the camera to the front.
        }
        mainCamera.update();
        System.out.println(mainCamera.position);
        mapRenderer.setView(mainCamera);
        mapRenderer.render();


    }


}
