package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListener implements Runnable {
    
    private Client client;
    private Socket socket;
    
    private BufferedReader socketIn;
    
    public ClientListener(Client client, Socket socket) {
        this.client = client;
        this.socket = socket;
    }
    
    public void run() {
        try {
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("IOException: unable to connect to server");
            e.printStackTrace();
        }
        
        String serverIn = null;
        try {
            while ((serverIn = socketIn.readLine()) != null) {
                client.recieveMessage(serverIn);
            }
        } catch (IOException e) {
            //TODO: reconnect to server here? like client.reconnect()
            System.out.println("IOException: unable to read from server");
            e.printStackTrace();
        }
    }
}
