package hw1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	String host;
	int portNum;
	ServerListener sl;
	Mediator m1;

	public Client(String host, int portNum) {
		this.host = host;
		this.portNum = portNum;
	}
	
	public void enterChatRoom() {
		try {
			Scanner in = new Scanner(System.in);
			System.out.print(">Enter your name: (Type in your name, then press Enter");
			String userName = in.next();
			in.reset();
			Socket socket = new Socket(host, portNum);
			sl = new ServerListener(this, socket);
			new Thread(sl).start();
			
			m1 = new Mediator(this, socket, userName);
			new Thread(m1).start();	
		}catch(IOException e) {
			System.out.println("Failure connecting to server");
			e.printStackTrace();	
		}
	}

	public void handleMessage(String cmd, String s) {
		System.out.println(cmd + s);
	}

	public static void main(String[] args) throws IOException {
		String hostName = "localhost";
		int portNumber = 4444;
		Client aUser = new Client(hostName, portNumber);
		aUser.enterChatRoom();
	} 

} 