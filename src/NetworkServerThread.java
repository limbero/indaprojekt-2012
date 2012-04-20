import java.io.*;
import java.net.*;
import java.util.Scanner;

public class NetworkServerThread extends Thread {
	private Socket socket = null;
	private BufferedReader in;
	private String owner;
	private String user;
	Scanner scanner;
	float x,y;

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

			boolean matchedhalf = false;
			boolean matched = false;

			out.println("host"); 
			inputLine = readLine();
			while(!inputLine.equals("exit")){
				if(!matched){
					if(inputLine.equals(owner)){
						System.out.println("right host");
						out.println("user");
						inputLine = readLine();
						matchedhalf = true;
					}
					if(inputLine.equals(user) && matchedhalf){
						out.println("done");
						System.out.println("user matched");
						matched = true;
					}
				}
				else{
					scanner = new Scanner(inputLine);
					if(scanner.hasNextFloat()){
						x=scanner.nextFloat();
						y=scanner.nextFloat();
					}
				}
				inputLine = "exit";
			}
			
			System.out.println("serverthreaded");
			
			out.close();
			in.close();
			socket.close();
		}catch (IOException e){
			e.printStackTrace();
		}

	}
}