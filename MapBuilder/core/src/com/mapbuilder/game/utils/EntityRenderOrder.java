package com.mapbuilder.game.utils;

public class EntityRenderOrder {

    private static String[] renderOrder = {"WorldEntity", "PlayerEntity"};

    public static String[] getRenderOrder(){
        return renderOrder;
    }
}