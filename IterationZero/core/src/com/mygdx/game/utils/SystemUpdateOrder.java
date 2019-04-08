package com.mygdx.game.utils;

public class SystemUpdateOrder {

    private static String[] updateOrder = {"InputSystem", "MovePlayerSystem", "RenderWorldSystem" , "RenderSystem"};

    public static String[] getUpdateOrder() {
        return updateOrder;
    }
}
