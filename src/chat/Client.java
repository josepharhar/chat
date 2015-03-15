package chat;

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
    private BufferedReader socketIn;
    private BufferedReader userIn;
    
    public Client(String hostname, int port, String username) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        
        try {
            socket = new Socket(hostname, port);
            
            socketOut = new PrintWriter(socket.getOutputStream(), true);
            
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            userIn = new BufferedReader(new InputStreamReader(System.in));
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public void run() throws IOException {
        System.out.println("Starting client on " + hostname + " port " + port);
        
        String userInput;
        while ((userInput = userIn.readLine()) != null) {
            socketOut.println(userInput);
            System.out.println("echo: " + socketIn.readLine());
        }
    }
}
