package com.mygdx.game.utils;

public class EntityRenderOrder {

    private static String[] renderOrder = {"WorldEntity", "PlayerEntity", "StaticEntity", "NPCEntity"};

    public static String[] getRenderOrder(){
        return renderOrder;
    }
}