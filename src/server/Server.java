package server;

import game.EntityManager;
import game.Player;

import java.io.*;
import java.net.*;

/**
 * Server for Peer to Peer
 */
public class Server extends P2P {
    int port;
    Socket socket;
    public ServerSocket serverSocket;
    OutputStream outputStream;
    InputStream inputStream;

    public Server(int port){
        this.port = port;
    }

    /**
     * Opens the server and waits for a client to connect
     * WARNING: Method will be stuck until a client has connected.
     * @throws IOException
     */
    public void connect() throws IOException, SocketException {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            socket = serverSocket.accept();
            System.out.println("New client connected");

            inputStream = socket.getInputStream();
            input = new ObjectInputStream(inputStream);
            outputStream = socket.getOutputStream();
            output = new ObjectOutputStream(outputStream);
            isConnected = true;
    }

    /**
     * Stops the server
     * @throws IOException
     */
    public void stop() throws IOException {
        serverSocket.close();
    }
}