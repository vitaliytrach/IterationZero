package components;

import interfaces.IComponent;

public class EntityStateComponent implements IComponent {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;

    private int id;
    private String type;
    private boolean[] availableDirections;
    public boolean isMoving;
    public boolean isAttacking;
    public String direction;

    public EntityStateComponent(int id) {
        this.id = id;
        type = "EntityStateComponent";
        isMoving = false;
        isAttacking = false;
        direction = "down";

        availableDirections = new boolean[4];
        availableDirections[UP] = true;
        availableDirections[DOWN] = true;
        availableDirections[RIGHT] = true;
        availableDirections[LEFT] = true;
    }

    public boolean hasNeighbor(int index) {
        return !availableDirections[index];
    }

    public void setAvailableDirections(boolean[] availableDirections) {
        this.availableDirections = availableDirections;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }
}
