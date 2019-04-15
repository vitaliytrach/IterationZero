package com.mygdx.game.utils;

import com.mygdx.game.components.MapComponent;
import com.mygdx.game.engine.ComponentManager;

/**
 * The MapUtils class is a class that assists in many operations that involve the Map entity.
 * It turns coordinates into a unique String key, and vice versa.
 * It uses the WallManager to check if a specific coordinate is not a wall.
 * It also can tell you which direction to move based looking at 2 different coordinates
 */
public class MapUtils {

    public static int NPC_MOVEMENT_DISTANCE = 10;
    public static ComponentManager cm = ComponentManager.getInstance();

    public static boolean isValidX(int x, int mapId) {
        if(Math.abs(x) <= NPC_MOVEMENT_DISTANCE / 2) {
            if(x < ((MapComponent) cm.getComponent(mapId, "MapComponent")).getWidth() - 1 || x > 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean isValidY(int y, int mapId) {
        if(Math.abs(y) <= NPC_MOVEMENT_DISTANCE / 2) {
            if(y < ((MapComponent) cm.getComponent(mapId, "MapComponent")).getHeight() - 1 || y > 0) {
                return true;
            }
        }

        return false;
    }

    public static String coordToString(int x, int y) {
        return Integer.valueOf(x) + "," + Integer.valueOf(y);
    }

    public static int[] coordToInt(String coord) {
        int[] result = new int[2];
        String[] coords = coord.split(",");
        result[0] = Integer.parseInt(coords[0]);
        result[1] = Integer.parseInt(coords[1]);
        return result;
    }

    public static String nextDirection(String start, String end) {
        String result = "up";

        int[] s = coordToInt(start);
        int[] e = coordToInt(end);

        int startX = s[0];
        int startY = s[1];

        int endX = e[0];
        int endY = e[1];

        if(startY != endY) {
            if(startY < endY) {
                result = "down";
            } else {
                result = "up";
            }
        } else if(startX != endX){
            if(startX < endX) {
                result = "right";
            } else {
                result = "left";
            }
        }

        return result;
    }
}
