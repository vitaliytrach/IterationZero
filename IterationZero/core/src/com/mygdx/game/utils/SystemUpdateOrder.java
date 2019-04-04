package com.mygdx.game.utils;

public class SystemUpdateOrder {

    private static String[] updateOrder = {"InputSystem", "RenderSystem"};

    public static String[] getUpdateOrder() {
        return updateOrder;
    }
}
