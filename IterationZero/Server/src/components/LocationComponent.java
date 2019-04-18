package components;

import interfaces.IComponent;

public class LocationComponent implements IComponent {

    private int id;
    private String type;
    public int map, x, y;

    public LocationComponent(int id, int map, int x, int y) {
        this.id = id;
        this.map = map;
        this.x = x;
        this.y = y;
        type = "LocationComponent";
    }

    @Override
    public int getID() { return id; }

    @Override
    public String getType() { return type; }
}
