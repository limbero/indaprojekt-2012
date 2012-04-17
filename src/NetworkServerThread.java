import java.io.*;
import java.net.*;

public class NetworkServerThread extends Thread {
    private Socket socket = null;
    private BufferedReader in;
    private String owner;
    private String user;
    
    PrintWriter out;
    public NetworkServerThread(Socket socket) {
        super("NetworkServerThread");
        this.socket = socket;
        owner = "limbero";
        user = "luddeha";
    }

    private String readLine() throws IOException {
        String str = in.readLine();
        return str;
    }

    public void run(){
         
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader
                (new InputStreamReader(socket.getInputStream()));
	
            String inputLine;
            
            boolean matched = false;
            
            out.println("host"); 
            inputLine = readLine();
            while(!inputLine.equals("exit")){
            	if(inputLine.equals(owner)){
            		System.out.println("right host");
            		out.println("user");
            		inputLine = readLine();
            		matched = true;
            	}
            	if(inputLine.equals(user) && matched){
        			out.println("Welcome "+user);
        		}
            }
            
            out.close();
            in.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    
    }
}