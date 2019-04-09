package com.mygdx.game.utils;

public class EntityRenderOrder {

    private static String[] renderOrder = {"WorldEntity", "PlayerEntity", "TreeEntity"};

    public static String[] getRenderOrder(){
        return renderOrder;
    }
}