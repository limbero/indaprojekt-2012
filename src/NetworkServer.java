import java.net.*;
import java.io.*;

public class NetworkServer {

    private static int connectionPort = 1415;
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = null;
       
        boolean listening = true;
        
        try {
            serverSocket = new ServerSocket(connectionPort); 
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + connectionPort);
            System.exit(1);
        }
	
        System.out.println("Server started listening on port: " + connectionPort);
        
        while (listening) {
            new NetworkServerThread(serverSocket.accept()).start();
        }
        
        serverSocket.close();
    }
}