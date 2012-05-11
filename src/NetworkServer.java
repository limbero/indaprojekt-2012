import java.io.*;
import java.net.*;
import java.util.*;

public class NetworkServer extends Thread {

	private ServerSocket server;


	public static void main(String args[]) throws Exception {
		new NetworkServer();
	}

	public NetworkServer() throws Exception {
		server = new ServerSocket(3000);
		System.out.println("Server listening on port 3000.");
		this.start();
	} 

	public void run() {
		while(true) {
			try {
				System.out.println("Waiting for connections.");
				Socket client = server.accept();
				System.out.println("Accepted a connection from: "+client.getInetAddress());
				Connect c = new Connect(client);
			} catch(Exception e) {}
		}
	}
}

class Connect extends Thread {
	private Socket client = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	public Connect() {}

	public Connect(Socket clientSocket) {
		client = clientSocket;
		try {
			ois = new ObjectInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());
		} catch(Exception e1) {
			try {
				client.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			return;
		}
		this.start();
	}


	public void run() {
		try {
			Player player = new Player("limbero");
			//player.setX(505);
			//player.setY(300);
			
			FileInputStream fis = new FileInputStream("player.txt");
		    ObjectInputStream ois2 = new ObjectInputStream(fis);
		    // read the objects from the input stream (the file name.out)
		    player = (Player) ois2.readObject();
		    ois2.close();
		    fis.close();
			
			oos.writeObject(player);
			
			System.out.println("Sent player successfully");
			
			oos.flush();
			
			/*Player player2 = new Player("luddeha");
			player2.setX(456);
			player2.setY(654);
			
			oos.writeObject(player2);
			
			oos.flush();*/
			
			// close streams and connections
			ois.close();
			oos.close();
			client.close(); 
		} catch(Exception e) {}       
	}
}