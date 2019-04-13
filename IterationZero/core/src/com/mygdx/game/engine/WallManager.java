package com.mygdx.game.engine;

import java.util.HashMap;

public class WallManager {

    private static WallManager instance;
    private HashMap<Integer, boolean[][]> walls;

    private WallManager() {
        walls = new HashMap<Integer, boolean[][]>();
    }

    public static WallManager getInstance() {
        if(instance == null) {
            instance = new WallManager();
        }

        return instance;
    }

    public void addMap(int mapId, int mapWidth, int mapHeight) {
        if(!walls.containsKey(mapId)) {

            boolean[][] mapWalls = new boolean[mapWidth][mapHeight];

            // Settings array of walls all to false
            for(int i = 0; i < mapWidth; i++) {
                for(int j = 0; j < mapHeight; j++) {
                    mapWalls[i][j] = false;
                }
            }

            walls.put(mapId, mapWalls);
        }
    }

    public boolean isWall(int mapId, int x, int y) {
        if(walls.containsKey(mapId)) {

            if(x > walls.get(mapId).length || y > walls.get(mapId)[0].length) {
                return true;
            }

            if(x < 0 || y < 0) {
                return true;
            }

            return walls.get(mapId)[x][y];
        }

        return true;
    }

    public void setWallStatus(int mapId, int x, int y, boolean status) {
        if(walls.containsKey(mapId)) {
            walls.get(mapId)[x][y] = status;
        }
    }
}
