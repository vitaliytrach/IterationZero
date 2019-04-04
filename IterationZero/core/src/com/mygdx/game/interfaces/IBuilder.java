package com.mygdx.game.interfaces;

import java.util.ArrayList;

public interface IBuilder {
    void buildComponentList();
    void buildSystemList();
    ArrayList<IComponent> getComponentList();
    ArrayList<ISystem> getSystemList();
    IEntity getEntity();
}
