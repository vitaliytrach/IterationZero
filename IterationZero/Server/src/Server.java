import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static double TICK_RATE = 60.0;

    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in =  null;

    private boolean running;
    private double currentTime, previousTime;

    // constructor with port
    public Server(int port) {

        running = true;
        previousTime = System.nanoTime();

        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            run();

            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }

        catch(IOException i) {
            System.out.println(i);
        }
    }

    public void run() {

        double elapsedTime = 0;

        while(running) {
            currentTime = System.nanoTime();
            elapsedTime += ((currentTime - previousTime) / 1000000000);
            previousTime = currentTime;

            if(elapsedTime >= (1.0 / TICK_RATE)) {
                elapsedTime = 0;
                tick();
            }
        }
    }

    private void tick() {

        String line = "";

        try {
            line = in.readUTF();
            System.out.println(line);

        } catch(IOException i) {
            System.out.println(i);
        }
    }
}
