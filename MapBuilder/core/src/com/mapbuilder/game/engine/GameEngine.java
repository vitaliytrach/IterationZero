package com.mapbuilder.game.engine;

import com.badlogic.ashley.core.Engine;

public class GameEngine {

    private static GameEngine instance;
    public Engine engine;

    public GameEngine() {
        engine = new Engine();
    }


    public static GameEngine getInstance() {
        if(instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }
}
