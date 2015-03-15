package chat;

import java.io.IOException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Chat {
    
    public static void main(String[] args) {
        try {
            start(args);
        } catch (ParseException e) {
            System.out.println("unable to parse command line arguments");
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            System.out.println("IOException.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("unable to parse port");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static void start(String[] args)
            throws ParseException, IOException, NumberFormatException {
        Options options = new Options();
        options.addOption("server", false, "Run chat as a server");
        options.addOption("port", true, "The port to connect on");
        options.addOption("hostname", true, "The server to connect to");
        options.addOption("username", false, "Your username");
        
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse(options, args);
        
        String hostname = cmd.getOptionValue("hostname");
        String username = cmd.hasOption("username") ? cmd.getOptionValue("username") : "anon";
        int port = Integer.parseInt(cmd.getOptionValue("port"));
        
        if (cmd.hasOption("server")) {
            // Running as Server
            Server server = new Server(port);
            server.run();
        } else {
            // Running as Client
            Client client = new Client(hostname, port, username);
            client.run();
        }
    }
}
