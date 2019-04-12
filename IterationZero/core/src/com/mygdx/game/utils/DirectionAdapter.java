package com.mygdx.game.utils;


public class DirectionAdapter {

    public static int stringToIntDirection(String direction) {

        int dir = 0;

        if(direction == "up") {
            dir = 0;
        } else if(direction == "down") {
            dir = 1;
        } else if(direction == "right") {
            dir = 2;
        } else if(direction == "left") {
            dir = 3;
        }

        return dir;
    }

    public static String intToStringDirection(int direction) {

        String dir = "up";

        if(direction == 0) {
            dir = "up";
        } else if(direction == 1) {
            dir = "down";
        } else if(direction == 2) {
            dir = "right";
        } else if(direction == 3) {
            dir = "left";
        }

        return dir;
    }
}
