package chat;

public class Chat {
    public static String hostname = "arhar.net";
    public static int port = 4880;
    
    public static void main(String[] args) {
        if (args.length == 2) {
            //client
            Client client = new Client(hostname, port);
            try {
                client.run();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        } else if (args.length == 1) {
            Server server = new Server(port);
            try {
                server.run();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        } else {
            System.out.println("bad arguments");
            System.exit(0);
        }
    }
}
