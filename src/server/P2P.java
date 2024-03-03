package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

/**
 * Handles Peer to Peer connection
 */
abstract public class P2P {
    Object lastObject;
    Object objectToSend = new Object();
    protected Boolean isConnected = false;
    protected Boolean connectionFailed = false;
    ObjectOutputStream output;
    ObjectInputStream input;

    public abstract void stop() throws IOException;
    public abstract void connect() throws IOException;

    /**
     * Listens for socket
     */
    public void listen(){
        try {
            lastObject = input.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Updates outputstream
     */
    public void update() {
        try {
            output.reset();
            output.writeObject(objectToSend);
            System.out.print("Objecttosend: ");
            System.out.println(objectToSend);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Set what object to update
     * @param obj
     */
    public void setUpdate(Object obj){
        this.objectToSend = obj;
    }

    /**
     * Returns lastobject that was read
     * @return
     */
    public Object read(){
        return lastObject;
    }

    /**
     * @return true if connected
     */
    public Boolean isConnected() {
        return isConnected;
    }

    /**
     * @return true if connection has failed
     */
    public Boolean isConnectionFailed(){
        return connectionFailed;
    }
}
