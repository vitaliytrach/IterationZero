package components;

import interfaces.IComponent;

public class PositionComponent implements IComponent {

    private int id;
    private String type;
    public float x, y;

    public PositionComponent(int id, float x, float y) {
        this.x = x;
        this.y = y;
        this.id = id;
        type = "PositionComponent";
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
