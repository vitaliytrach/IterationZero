package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class JsonUtil {

    public static void readFile(String filepath) {

        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal(filepath));

        System.out.println(base.toString());
    }
}
