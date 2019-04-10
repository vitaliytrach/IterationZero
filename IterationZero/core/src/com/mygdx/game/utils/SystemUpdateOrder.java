package com.mygdx.game.utils;

public class SystemUpdateOrder {

    private static String[] updateOrder = {"MovementSystem", "RenderWorldSystem" , "AnimationSystem", "RenderSystem"};

    public static String[] getUpdateOrder() {
        return updateOrder;
    }
}
