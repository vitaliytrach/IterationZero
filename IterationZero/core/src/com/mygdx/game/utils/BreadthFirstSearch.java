package com.mygdx.game.utils;

import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.components.MapComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.WallManager;

import java.util.*;

public class BreadthFirstSearch {

    public static WallManager wm = WallManager.getInstance();
    public static ComponentManager cm = ComponentManager.getInstance();

    public static ArrayList<String> bfsPath(int entityId, int startX, int startY) {
        HashSet<String> visited = new HashSet<String>();
        ArrayList<String> path = new ArrayList<String>();
        ArrayList<String> queue = new ArrayList<String>();
        HashMap<String, String> myPath = new HashMap<String, String>();

        int x = startX;
        int y = startY;

        LocationComponent lc = (LocationComponent) cm.getComponent(entityId, "LocationComponent");
        int[] endPoints = findNextPath(lc);
        int mapId = lc.getMap();

        int endX = endPoints[0];
        int endY = endPoints[1];

        String startNode = MapUtils.coordToString(x, y);
        String endNode = MapUtils.coordToString(endX, endY);
        String curr = startNode;
        String node;

        visited.add(curr);

        while(true) {

            // Up
            node = MapUtils.coordToString(x, y - 1);
            if(!visited.contains(node) && MapUtils.isValidY(y - 1, mapId)) {
                queue.add(node);
                visited.add(node);
                myPath.put(node, curr);
            }

            // Down
            node = MapUtils.coordToString(x, y + 1);
            if(!visited.contains(node) && MapUtils.isValidY(y + 1, mapId)) {
                queue.add(node);
                visited.add(node);
                myPath.put(node, curr);
            }

            // Right
            node = MapUtils.coordToString(x + 1, y);
            if(!visited.contains(node) && MapUtils.isValidX(x + 1, mapId)) {
                queue.add(node);
                visited.add(node);
                myPath.put(node, curr);
            }

            // Left
            node = MapUtils.coordToString(x - 1, y);
            if(!visited.contains(node) && MapUtils.isValidX(x - 1, mapId)) {
                queue.add(node);
                visited.add(node);
                myPath.put(node, curr);
            }

            if(queue.isEmpty()) {
                break;
            }

            if(curr.compareTo(endNode) == 0) {

                String temp = curr;

                while(true) {
                    path.add(temp);

                    if(temp.compareTo(startNode) == 0) {
                        break;
                    }

                    temp = myPath.get(temp);
                }

                break;
            }

            curr = queue.remove(0);
            int[] newCoords = MapUtils.coordToInt(curr);
            x = newCoords[0];
            y = newCoords[1];
        }

        Collections.reverse(path);
        return path;
    }

    public static int[] findNextPath(LocationComponent lc) {
        int[] result = new int[2];

        Random rand = new Random();
        int distance = MapUtils.NPC_MOVEMENT_DISTANCE;
        MapComponent mc = (MapComponent) cm.getComponent(lc.getMap(), "MapComponent");

        int x = lc.getX();
        int y = lc.getY();

        int randStartX = x - (distance / 2) > 0 ? x - (distance / 2) : 0;
        int randEndX = x + (distance / 2) < mc.getWidth() ? x + (distance / 2) : mc.getWidth() - 1;
        int randStartY = y - (distance / 2) > 0 ? y - (distance / 2) : 0;
        int randEndY = y + (distance / 2) < mc.getHeight() ? y + (distance / 2) : mc.getHeight() - 1;

        int xPos = 0;
        int yPos = 0;

        while(true) {
            xPos = rand.nextInt(randEndX) + randStartX;
            yPos = rand.nextInt(randEndY) + randStartY;

            if(!wm.isWall(lc.getMap(), xPos, yPos)) {
                break;
            }
        }

        result[0] = xPos;
        result[1] = yPos;
        return result;
    }
}
