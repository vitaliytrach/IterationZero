package com.mygdx.game.utils;

public class SystemUpdateOrder {

    private static String[] updateOrder = {"CollisionDetectionSystem", "MovementSystem", "AttackSystem", "NPCMovementSystem",
            "RenderWorldSystem" , "AnimationSystem", "RenderSystem", "SendPacketSystem"};

    public static String[] getUpdateOrder() {
        return updateOrder;
    }
}
