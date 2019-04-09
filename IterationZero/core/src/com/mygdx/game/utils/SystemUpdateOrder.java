package com.mygdx.game.utils;

public class SystemUpdateOrder {

    private static String[] updateOrder = {"MovementSystem", "RenderWorldSystem" , "RenderSystem"};

    public static String[] getUpdateOrder() {
        return updateOrder;
    }
}
