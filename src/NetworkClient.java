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

	public NetworkClient(String ip, Player player, Player hostingPlayer){
		p1 = player;
		hp = hostingPlayer;
		adress = ip;
		user = player.getName();
		host = hostingPlayer.getName();

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
				}
				if(line.equals("user")){
					out.println(user);
					b = true;
				}
				if(line.equals("done")){
					c=true;
				}
			}
			try{
				line = in.readLine();
			} catch (IOException e) {}
		}
		connected = true;
	}

	public boolean connected(){
		return connected;
	}

	public void start(){
		if(connected){
			String coords = "";
			coords = p1.getX()+" "+p1.getY();
			out.println(coords);

			while(!coords.equals("exit")){
				out.println(coords);
			}

			out.close();
			try{
				in.close();
				ServerSocket.close();
			} catch (IOException e) {}
		}
	}
}   