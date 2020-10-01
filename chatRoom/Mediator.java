package hw1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Mediator implements Runnable{
	Client c;
	PrintWriter out;
	String name;
	
	Mediator(Client c, Socket socket, String userName) throws IOException{
		this.c = c;
		out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
		name = userName;
	}
	
	@Override
	public void run() {
		Scanner reader = new Scanner(System.in);
		out.println(name);
		String input;
		while(true) {
			input = reader.nextLine();
			out.println(name + " " + input); 			
			out.flush(); 
			if(input.equalsIgnoreCase("/quit")) {
				out.println("quit" + name);
				break;
			}
		}
		System.out.println("You have left the chatroom");
		System.exit(-1);
	}
}
