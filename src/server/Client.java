package server;

import game.EntityManager;
import java.net.*;
import java.io.*;

/**
 * Client for Peer to Peer connection
 */
public class Client extends P2P {
    String hostname;
    int port;
    Socket socket;
    OutputStream outputStream;
    InputStream inputStream;

    /**
     * @param hostname for server
     * @param port for server
     */
    public Client(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Stops client
     * @throws IOException
     */
    public void stop() throws IOException{

    }

    /**
     * Connects client to server
     * @throws IOException
     */
    public void connect() throws IOException {
            System.out.println("hello i am connecting");
            socket = new Socket(hostname, port);
            outputStream = socket.getOutputStream();
            output = new ObjectOutputStream(outputStream);
            inputStream = socket.getInputStream();
            input = new ObjectInputStream(inputStream);
            isConnected = true;
            connectionFailed = false;
    }
}

