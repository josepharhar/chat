package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private String hostname;
    private int port;
    private String username;
    
    private Socket socket;
    private PrintWriter socketOut;
    private BufferedReader userIn;
    
    private ClientListener listener;
    
    public Client(String hostname, int port, String username) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
    }
    
    public void recieveMessage(String message) {
        System.out.println(message);
    }
    
    public void start() throws IOException {
        System.out.println("Starting client on " + hostname + " port " + port);
        
        socket = new Socket(hostname, port);
        socketOut = new PrintWriter(socket.getOutputStream(), true);
        userIn = new BufferedReader(new InputStreamReader(System.in));
        
        // Set up input from server
        listener = new ClientListener(this, socket);
        new Thread(listener).start();
        
        String userInput = null;
        while ((userInput = userIn.readLine()) != null) {
            socketOut.println("[" + username + "]: " + userInput);
        }
    }
}
