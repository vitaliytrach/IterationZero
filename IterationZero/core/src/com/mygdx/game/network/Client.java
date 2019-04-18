package com.mygdx.game.network;

// A Java program for a Client
import java.net.*;
import java.io.*;

// JUST TESTING BASIC CLIENT/SERVER STUFF

public class Client {

    private static Client instance;

    public boolean connected = false;

    // initialize socket and input output streams
    private Socket socket            = null;
    private DataOutputStream out     = null;

    public static Client getInstance() {
        if(instance == null) {
            instance = new Client();
        }

        return instance;
    }

    // constructor to put ip address and port
    private Client() {

        String address = "127.0.0.1";
        int port = 5000;

        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            connected = true;
            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u) {
            System.out.println(u);
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }

    public void direction(String direction) {
        String data = "Player is moving " + direction;

        try {
            out.writeUTF(data);
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }

    public void sendPacket(StringBuilder sb) {
        try {
            out.writeUTF(sb.toString());
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }

    public void closeConnection() {

        System.out.println("Closing connection!");

        // close the connection
        try {
            out.writeUTF("Over");
            out.close();
            socket.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }
}
