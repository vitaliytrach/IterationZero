package com.mygdx.game.utils;

public class EntityIDs {
    public static final int PLAYER_ID = -1;
    public static final int WORLD_ID = 2;
    public static final int TILE_ID = 1;

    private static int nextID = 1000;

    public static int getNextID() {
        nextID++;
        return nextID;
    }
}
