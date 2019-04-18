package com.mygdx.game.systems.net;

import com.mygdx.game.components.EntityStateComponent;
import com.mygdx.game.components.LocationComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.engine.ComponentManager;
import com.mygdx.game.interfaces.ISystem;
import com.mygdx.game.network.Client;

public class SendPacketSystem implements ISystem {

    private int id;
    private String type;
    private Client client;
    private ComponentManager cm;

    public SendPacketSystem(int id) {
        this.id = id;
        type = "SendPacketSystem";
        client = Client.getInstance();
        cm = ComponentManager.getInstance();
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

        if(client.connected) {
            StringBuilder sb = new StringBuilder();

            LocationComponent lc = (LocationComponent) cm.getComponent(id, "LocationComponent");
            EntityStateComponent esc = (EntityStateComponent) cm.getComponent(id, "EntityStateComponent");
            TransformComponent tc = (TransformComponent) cm.getComponent(id, "TransformComponent");

            sb.append(id + "," + lc.getMap() + ", " + lc.getX() + ", " + lc.getY());
            client.sendPacket(sb);
        }
    }
}
