package server;

import java.io.IOException;

/**
 * Thread for handling Peer to peer listening
 */
public class ListenThread extends Thread {
    private P2P connection;

    public ListenThread(P2P connection) {
        this.connection = connection;
    }

    public void run() {
        while (true) {
            System.out.println("THREAD RUNNING");
            if (connection.isConnected()) {
                connection.listen();
            } else {
                System.out.println("Not connected");
            }
        }
    }
}
