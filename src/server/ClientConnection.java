package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection implements Runnable {
    
    private Server server;
    private Socket clientSocket;
    private PrintWriter clientOut;
    private BufferedReader clientIn;

    public ClientConnection(Server server, Socket socket) throws IOException {
        this.server = server;
        this.clientSocket = socket;
    }
    
    // Send a message to the client
    public void sendMessage(String message) {
        clientOut.println(message);
    }
    
    public void run() {
        String inputLine = null;

        try {
            clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
            clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while ((inputLine = clientIn.readLine()) != null) {
                server.sendMessage(inputLine);
            }
        } catch (IOException e) {
            System.out.println("error reading from client");
            e.printStackTrace();
        }
    }
}
