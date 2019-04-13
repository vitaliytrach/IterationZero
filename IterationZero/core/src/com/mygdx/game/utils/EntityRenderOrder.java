package com.mygdx.game.utils;

public class EntityRenderOrder {

    private static String[] renderOrder = {"WorldEntity", "StaticEntity", "NPCEntity", "PlayerEntity"};

    public static String[] getRenderOrder(){
        return renderOrder;
    }
}