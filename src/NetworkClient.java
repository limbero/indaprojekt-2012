import java.io.*;   
import java.net.*;  
import java.util.Scanner;

public class NetworkClient {
    private static int port = 1415;
    private static String host = "limbero";
    private static String user = "luddeha";
    
    public static void main(String[] args) throws IOException {
        Socket ServerSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String adress = "localhost";

        /*try {
            adress = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing argument ip-adress");
            System.exit(1);
        }*/
        try {
            ServerSocket = new Socket(adress, port); 
            out = new PrintWriter(ServerSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader
                                    (ServerSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " +adress);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't open connection to " + adress);
            System.exit(1);
        }

        System.out.println("Contacting server ... ");
        String line = in.readLine();
        System.out.println(line);

        boolean a = false;
        boolean b = false;
        while(!a || !b){
        	if(line != null){
        		if(line.equals("host")){
        			System.out.println("sending "+host);
        			out.println(host);
        			a = true;
        		}
        		if(line.equals("user")){
        			System.out.println("host matched, sending "+user);
        			out.println(user);
        			b = true;
        		}
        	}
        	line = in.readLine();
        }
        System.out.println("user matched.");

        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String menuOption = scanner.next();
        out.println(menuOption);
        
        while(!menuOption.equals("exit")){
        	
        }
        
        out.close();
        in.close();
        ServerSocket.close();
    }
}   