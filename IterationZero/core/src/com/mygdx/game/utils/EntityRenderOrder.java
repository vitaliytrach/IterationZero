package com.mygdx.game.utils;

public class EntityRenderOrder {

    private static String[] renderOrder = {"TileEntity", "PlayerEntity"};

    public static String[] getRenderOrder(){
        return renderOrder;
    }
}