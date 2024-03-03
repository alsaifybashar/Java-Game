package server;

import java.io.IOException;

/**
 * For handling Multiplayer connection
 */
public enum Multiplayer {
    host(true), guest(false);
    private P2P connection;
    private ListenThread thread;
    private Boolean isHost;

    Multiplayer(Boolean isHost) {
        this.isHost = isHost;
    }

    /**
     * Connects
     * @param ip of host
     * @param port for connection
     * @throws IOException
     */
    public void connect(String ip, int port) throws IOException {
        if (isHost) connection = new Server(port);
        else connection = new Client(ip, port);
        connection.connect();
    }

    /**
     * Starts listening thead
     */
    public void start() {
        System.out.print("STARTING THREAD");
        thread = new ListenThread(connection);
        thread.start();
    }

    /**
     * @return connection
     */
    public P2P getConnection() {
        return connection;
    }

    /**
     * Stops the connection
     */
    public void stop() {
        try {
            connection.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return true if connected
     */
    public Boolean isConnected() {
        return getConnection().isConnected();
    }
}
