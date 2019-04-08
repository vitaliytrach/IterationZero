package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.data.Tile;

import java.util.ArrayList;

public class JsonUtil {

    public static Tile[] getMap(String filepath) {

        Tile[] tiles;

        Json json = new Json();
        ArrayList<JsonValue> input = json.fromJson(ArrayList.class, Gdx.files.internal(filepath));
        tiles = new Tile[input.size()];

        for(int i = 0; i < input.size(); i++) {
            String path = input.get(i).getString("sprite");
            int x = input.get(i).getInt("x");
            int y = input.get(i).getInt("y");
            tiles[i] = new Tile(x, y, path);
        }

        return tiles;
    }
}
