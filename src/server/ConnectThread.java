package server;

import java.io.IOException;
import java.net.SocketException;
import java.sql.Connection;

/**
 * Thread for handling Peer to peer connection
 */
public class ConnectThread extends Thread {
    private P2P connection;

    public ConnectThread(P2P connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            connection.connect();
        } catch (SocketException e){
            System.out.println("Socket was closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
