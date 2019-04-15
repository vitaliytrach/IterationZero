package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.components.MapComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.data.Tile;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.engine.WallManager;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.utils.DirectionAdapter;
import com.mygdx.game.utils.EntityIDs;

import java.util.*;

public class NPCMovementSystem implements ISystem {

    private static final int NPC_MOVEMENT_DISTANCE = 10;

    private int id;
    private String type;
    private ComponentManager cm;
    private WallManager wm;
    private int moveP;
    private Random rand;
    private float moveX, moveY;
    private int direction, counter;
    private String[] p;
    private boolean shouldFindPath;
    private ArrayList<String> tempPath;
    private float deltaTime;
    private int targetGoal = 5;

    public NPCMovementSystem(int id) {
        this.id = id;
        type = "NPCMovementSystem";
        moveP = 50;
        cm = ComponentManager.getInstance();
        wm = WallManager.getInstance();
        rand = new Random();
        direction = 0;
        counter = 0;
        shouldFindPath = true;
        deltaTime = 0;
        tempPath = new ArrayList<String>();
        moveX = (Tile.DEFAULT_TILE_WIDTH / 2) / MovementSystem.TICKS_PER_BLOCK_MOVEMENT;
        moveY = (Tile.DEFAULT_TILE_HEIGHT / 2)/ MovementSystem.TICKS_PER_BLOCK_MOVEMENT;
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
        EntityStateComponent esc = (EntityStateComponent) cm.getComponent(id, "EntityStateComponent");
        LocationComponent lc = (LocationComponent) cm.getComponent(id, "LocationComponent");
        MapComponent mc = (MapComponent) cm.getComponent(lc.getMap(), "MapComponent");

        deltaTime += Gdx.graphics.getDeltaTime();

        if(!esc.isMoving()) {

            if(shouldFindPath) {
                tempPath = findNextPath();

                shouldFindPath = false;
            }

            if(deltaTime < targetGoal) {
                return;
            }

            targetGoal = rand.nextInt(5);
            deltaTime = 0;

            if(tempPath.size() <= 1) {
                shouldFindPath = true;
                return;
            }

            String d1 = tempPath.remove(0);
            String dir = nextDirection(d1, tempPath.get(0));

            if(dir == "up") {
                if((lc.getY() - 1) < 0) { return; }
                lc.setY(lc.getY() - 1);
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY);
                esc.setMoveStatus(true);
            } else if(dir == "down") {
                if((lc.getY() + 1) > mc.getHeight() - 1) { return; }
                lc.setY(lc.getY() + 1);
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY) * -1;
                esc.setMoveStatus(true);
            } else if(dir == "right") {
                if((lc.getX() + 1) > mc.getWidth()) { return; }
                lc.setX(lc.getX() + 1);
                moveX = Math.abs(moveX);
                moveY = Math.abs(moveY) * -1;
                esc.setMoveStatus(true);
            } else if(dir == "left") {
                if((lc.getX() - 1) < 0) { return; }
                lc.setX(lc.getX() - 1);
                moveX = Math.abs(moveX) * -1;
                moveY = Math.abs(moveY);
                esc.setMoveStatus(true);
            }
        }

        if(esc.isMoving()) {
            if(counter >= MovementSystem.TICKS_PER_BLOCK_MOVEMENT) {
                if(tempPath.size() <= 1) {
                    shouldFindPath = true;
                }

                counter = 0;
                moveX = (Tile.DEFAULT_TILE_WIDTH / 2) / MovementSystem.TICKS_PER_BLOCK_MOVEMENT;
                moveY = (Tile.DEFAULT_TILE_HEIGHT / 2)/ MovementSystem.TICKS_PER_BLOCK_MOVEMENT;
                esc.setMoveStatus(false);
                return;
            }

            move();
        }
    }

    private ArrayList<String> findNextPath() {
        LocationComponent lc = (LocationComponent) cm.getComponent(id, "LocationComponent");
        MapComponent mc = (MapComponent) cm.getComponent(lc.getMap(), "MapComponent");
        int x = lc.getX();
        int y = lc.getY();

        int randStartX = x - (NPC_MOVEMENT_DISTANCE / 2) > 0 ? x - (NPC_MOVEMENT_DISTANCE / 2) : 0;
        int randEndX = x + (NPC_MOVEMENT_DISTANCE / 2) < mc.getWidth() ? x + (NPC_MOVEMENT_DISTANCE / 2) : mc.getWidth() - 1;
        int randStartY = y - (NPC_MOVEMENT_DISTANCE / 2) > 0 ? y - (NPC_MOVEMENT_DISTANCE / 2) : 0;
        int randEndY = y + (NPC_MOVEMENT_DISTANCE / 2) < mc.getHeight() ? y + (NPC_MOVEMENT_DISTANCE / 2) : mc.getHeight() - 1;

        int xPos = 0;
        int yPos = 0;

        while(true) {
            xPos = rand.nextInt(randEndX) + randStartX;
            yPos = rand.nextInt(randEndY) + randStartY;

            if(!wm.isWall(lc.getMap(), xPos, yPos)) {
                break;
            }
        }

        return bfsPath(lc.getMap(), x, y, xPos, yPos);
    }

    private ArrayList<String> bfsPath(int mapId, int startX, int startY, int endX, int endY) {
        HashSet<String> visited = new HashSet<String>();
        ArrayList<String> path = new ArrayList<String>();
        ArrayList<String> queue = new ArrayList<String>();
        HashMap<String, String> myPath = new HashMap<String, String>();

        int x = startX;
        int y = startY;

        String startNode = coordToString(x, y);
        String endNode = coordToString(endX, endY);
        String curr = startNode;

        visited.add(curr);

        while(true) {

            // Up
            if(!visited.contains(coordToString(x, y - 1)) && isValidY(y - 1)) {
                String node = coordToString(x, y - 1);
                queue.add(node);
                visited.add(node);
                myPath.put(node, curr);
            }

            // Down
            if(!visited.contains(coordToString(x, y + 1)) && isValidY(y + 1)) {
                String node = coordToString(x, y + 1);
                queue.add(node);
                visited.add(node);
                myPath.put(node, curr);
            }

            // Right
            if(!visited.contains(coordToString(x + 1, y)) && isValidX(x + 1)) {
                String node = coordToString(x + 1, y);
                queue.add(node);
                visited.add(node);
                myPath.put(node, curr);
            }

            // Left
            if(!visited.contains(coordToString(x - 1, y)) && isValidX(x - 1)) {
                String node = coordToString(x - 1, y);
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
            int[] newCoords = coordToInt(curr);
            x = newCoords[0];
            y = newCoords[1];
        }

        Collections.reverse(path);
        return path;
    }

    private boolean isValidX(int x) {
        if(Math.abs(x) <= NPC_MOVEMENT_DISTANCE / 2) {
            if(x < ((MapComponent) cm.getComponent(1, "MapComponent")).getWidth() - 1 || x > 0) {
                return true;
            }
        }

        return false;
    }

    private String nextDirection(String start, String end) {
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

    private boolean isValidY(int y) {
        if(Math.abs(y) <= NPC_MOVEMENT_DISTANCE / 2) {
            if(y < ((MapComponent) cm.getComponent(1, "MapComponent")).getHeight() - 1 || y > 0) {
                return true;
            }
        }

        return false;
    }
    
    private String coordToString(int x, int y) {
        return Integer.valueOf(x) + "," + Integer.valueOf(y);
    }

    private int[] coordToInt(String cord) {
        int[] result = new int[2];
        String[] cords = cord.split(",");
        result[0] = Integer.parseInt(cords[0]);
        result[1] = Integer.parseInt(cords[1]);
        return result;
    }

    private void move() {
        TransformComponent tc = (TransformComponent) cm.getComponent(id, "TransformComponent");
        tc.setPostion(new Vector2(tc.getPosition().x + moveX, tc.getPosition().y + moveY));
        counter++;
    }
}
