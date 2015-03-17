package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private int port;
    
    private ServerSocket serverSocket;
    
    private List<ClientConnection> clients;
    
    public Server(int port) {
        this.port = port;
        
        clients = new ArrayList<ClientConnection>();
    }
    
    public void start() throws IOException {
        System.out.println("Starting server on port " + port);
        serverSocket = new ServerSocket(port);
        while (true) {
            addClient(serverSocket.accept());
        }
    }
    
    // Make a new client, start it, and add it to the list
    public void addClient(Socket clientSocket) throws IOException {
        ClientConnection newClient = new ClientConnection(this, clientSocket);
        new Thread(newClient).start();
        clients.add(newClient);
        System.out.println("added client");
    }
    
    public void sendMessage(String message) {
        System.out.println(message);
        for (ClientConnection client : clients) {
            new Thread(() -> client.sendMessage(message)).start();
        }
    }
}
