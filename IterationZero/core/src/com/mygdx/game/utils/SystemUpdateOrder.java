package com.mygdx.game.utils;

public class SystemUpdateOrder {

    private static String[] updateOrder = {"CollisionDetectionSystem", "MovementSystem", "AttackSystem", "NPCMovementSystem",
            "RenderWorldSystem" , "AnimationSystem", "RenderSystem"};

    public static String[] getUpdateOrder() {
        return updateOrder;
    }
}
