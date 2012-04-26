import java.io.*;   
import java.net.*;  
import java.util.Scanner;

public class NetworkClient {
	private static int port = 14115;
	Socket ServerSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	Player p1, hp;

	String adress, user, host, line;

	boolean connected = false;

	public NetworkClient(String ip, int serverPort, Player player){
		p1 = player;
		
		adress = ip;
		port =  serverPort;
		
		user = player.getName();
		host = "server";

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

		try{
			line = in.readLine();
		} catch (IOException e) {}

		boolean a = false;
		boolean b = false;
		boolean c = false;
		while(!a || !b || !c){
			if(line != null){
				if(line.equals("host")){
					out.println(host);
					a = true;
					System.out.println("host ident");
				}
				if(line.equals("user")){
					out.println(user);
					b = true;
					System.out.println("user ident");
				}
				if(line.equals("done")){
					c=true;
					System.out.println("totl ident");
				}
			}
			try{
				line = in.readLine();
			} catch (IOException e) {}
		}
		connected = true;
	}

	public boolean connected(){
		if(connected){
			if(ping().equals("pong")){
				return true;
			}
		}
		return false;
	}
	
	public String ping(){
		String s = send("ping");
		return s;
	}

	public String send(String s){
		if(connected){
			out.println(s);
		}
		line = "";
		while(line.equals("")){
			try{
				line = in.readLine();
			} catch (IOException e) {}
		}
		return line;
	}
}   