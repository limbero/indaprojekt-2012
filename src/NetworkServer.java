import java.net.*;
import java.io.*;

public class NetworkServer {

    int connectionPort = 14115;
    NetworkServerThread NST;
    ServerSocket serverSocket;
    
    public static void main(String args[]){
    	try{
    		NetworkServer netserv = new NetworkServer();
    	}catch (IOException e){}
    }
    
    public NetworkServer() throws IOException {
        
        serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(connectionPort); 
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + connectionPort);
            System.exit(1);
        }
	
        System.out.println("Server started listening on port: " + connectionPort);
        
        NST = new NetworkServerThread(serverSocket.accept());
        NST.start();
    }
    
    public void stop(){
    	try {
			serverSocket.close();
		} catch (IOException e) {}
    }
    
    public float getX(){
    	return NST.x;
    }
    
    public float getY(){
    	return NST.y;
    }
}