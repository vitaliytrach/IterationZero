package com.mygdx.game.systems;

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

    public NPCMovementSystem(int id) {
        this.id = id;
        type = "NPCMovementSystem";
        moveP = 50;
        cm = ComponentManager.getInstance();
        wm = WallManager.getInstance();
        rand = new Random();
        direction = 0;
        counter = 0;
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

    boolean test = true;

    @Override
    public void render() {
        EntityStateComponent esc = (EntityStateComponent) cm.getComponent(id, "EntityStateComponent");
        LocationComponent lc = (LocationComponent) cm.getComponent(id, "LocationComponent");

        if(test) {
            findNextPath();
            test = false;
        }

        if(!esc.isMoving()) {
            int roll = rand.nextInt(100);

            if(roll <= moveP) {
                direction = rand.nextInt(4);

                if(esc.hasNeighbor(direction)) {
                    moveP = moveP + (moveP >> 1);
                }  else {
                    moveP = 50;
                    String dir = DirectionAdapter.intToStringDirection(direction);
                    MapComponent mc = (MapComponent) cm.getComponent(EntityIDs.WORLD_ID, "MapComponent");

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
            }
        }

        if(esc.isMoving()) {
            if(counter >= MovementSystem.TICKS_PER_BLOCK_MOVEMENT) {
                counter = 0;
                moveX = (Tile.DEFAULT_TILE_WIDTH / 2) / MovementSystem.TICKS_PER_BLOCK_MOVEMENT;
                moveY = (Tile.DEFAULT_TILE_HEIGHT / 2)/ MovementSystem.TICKS_PER_BLOCK_MOVEMENT;
                esc.setMoveStatus(false);
                return;
            }

            move();
        }
    }

    private void findNextPath() {
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

        String[] path = dfsPath(lc.getMap(), x, y, xPos, yPos);

        for(String s : path) {
            System.out.println(s);
        }
    }

    private String[] dfsPath(int mapId, int startX, int startY, int endX, int endY) {
        ArrayList<String> path = new ArrayList<String>();
        HashSet<String> visited = new HashSet<String>();
        ArrayList<String> queue = new ArrayList<String>();

        int x = startX;
        int y = startY;
        int totalX = 0;
        int totalY = 0;

        String curr = coordToString(x, y);
        String end = coordToString(endX, endY);

        System.out.println(curr + " looking for: " + end);

        while(true) {
            path.add(curr);

            // Check if up is visited
            if(!visited.contains(coordToString(x,y - 1))) {
                if(!wm.isWall(mapId, x, y - 1) && isValidY(y - 1, totalY)) {
                    queue.add(coordToString(x, y - 1));
                    visited.add(coordToString(x, y - 1));
                    path.add("up");
                    totalY--;
                }
            }

            // Check if down is visited
            if(!visited.contains(coordToString(x,y + 1))) {
                if(!wm.isWall(mapId, x, y + 1) && isValidY(y + 1, totalY)) {
                    queue.add(coordToString(x, y + 1));
                    visited.add(coordToString(x, y + 1));
                    path.add("down");
                    totalY++;
                }
            }

            // Check if right is visited
            if(!visited.contains(coordToString(x + 1, y))) {
                if(!wm.isWall(mapId, x + 1, y) && isValidX(x + 1, totalX)) {
                    queue.add(coordToString(x + 1, y));
                    visited.add(coordToString(x + 1, y));
                    path.add("right");
                    totalX++;
                }
            }

            // Check if left is visited
            if(!visited.contains(coordToString(x - 1,y))) {
                if(!wm.isWall(mapId, x - 1, y) && isValidX(x - 1, totalX)) {
                    queue.add(coordToString(x - 1, y));
                    visited.add(coordToString(x - 1, y));
                    path.add("left");
                    totalX--;
                }
            }

            if(curr.compareTo(end) == 0) {
                break;
            }

            visited.add(curr);

            if(queue.isEmpty()) {
                break;
            }

            int[] nextCoords = coordToInt(curr);
            curr = queue.remove(0);
            x = nextCoords[0];
            y = nextCoords[1];
        }

        return path.toArray(new String[0]);
    }

    private boolean isValidX(int x, int totalX) {
        if(Math.abs(x) <= NPC_MOVEMENT_DISTANCE / 2) {
            if(x < ((MapComponent) cm.getComponent(1, "MapComponent")).getWidth() - 1 || x > 0) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidY(int y, int totalY) {
        if(Math.abs(y) <= NPC_MOVEMENT_DISTANCE / 2) {
            if(y < ((MapComponent) cm.getComponent(1, "MapComponent")).getHeight() - 1 || y > 0) {
                return true;
            }
        }

        return false;
    }

    private String coordToString(int x, int y) {
        return Integer.toString(x) + "," + Integer.toString(y);
    }

    private int[] coordToInt(String coords) {
        int[] result = new int[2];
        String[] coord = coords.split(",");
        result[0] = Integer.parseInt(coord[0]);
        result[1] = Integer.parseInt(coord[1]);
        return result;
    }

    private void move() {
        TransformComponent tc = (TransformComponent) cm.getComponent(id, "TransformComponent");
        tc.setPostion(new Vector2(tc.getPosition().x + moveX, tc.getPosition().y + moveY));
        counter++;
    }
}
