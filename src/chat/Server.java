package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    
    private PrintWriter clientOut;
    private BufferedReader clientIn;
    
    public Server(int port) {
        this.port = port;
        
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            
            clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
            clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() throws IOException {
        String inputLine;

        while ((inputLine = clientIn.readLine()) != null) {
            System.out.println("got from client: " + inputLine);
            clientOut.println("back to client: " + inputLine);
        }
    }
}
