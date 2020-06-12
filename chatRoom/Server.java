package hw1;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	private int portNum;
	public static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
	
	public Server(int portNum) {
		this.portNum = portNum;
	}
	
	public static void main(String[] args) throws IOException {
		Server server = new Server(4444);
		server.initilizeRoom();
	} 
	
	
	public void initilizeRoom() {
		System.out.println("Initilizing Room");
		try(ServerSocket sSocket = new ServerSocket(portNum)){
			//forever loop
			while(true) {
				Socket socket = sSocket.accept();
				ClientHandler aClient = new ClientHandler(socket, this);
				clients.add(aClient);
				aClient.start();
			}
		}catch(IOException e) {
			System.out.println("Error in loading port: " + portNum);
			e.printStackTrace();
		}
	}
	
	public void broadcast(String user, String message, ClientHandler client) {
		for(ClientHandler c: clients) {
			if(!c.equals(client))
			c.handleRequest(user, message);
		}
	}
} 

class ClientHandler extends Thread {
	private Socket socket; 
	private String lastMessage;
	private Server chatRoom;
	private PrintWriter out;
	private Scanner in;
	
	ClientHandler(Socket socket, Server chatRoom){
		this.socket = socket;
		this.chatRoom = chatRoom;
	}
	
	public void run() { 
		try {
			in = new Scanner(new BufferedInputStream(socket.getInputStream())); 
			out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
			
			out.println("Welcome to the Chat Room, to leave just type /quit");
			out.flush(); 
			
			String cmd, msg;
			while (true) {
				cmd = in.next();
				msg = in.nextLine();
				chatRoom.broadcast(cmd, msg, this);
				out.flush();
				if(msg.equalsIgnoreCase("/quit")) {
					System.out.println(cmd + " has left the chatroom");
				}
			}
		} catch (IOException e) {
			System.out.println("failure to handle client");
			e.printStackTrace();
		}
	} 
	
	public void handleRequest(String cmd, String message) {
		if(message.trim().equals("/quit")) {
			System.out.println(cmd + " has left the chatroom");
		}
		else {
			lastMessage = cmd + ": " + message;
			System.out.println(lastMessage);
			out.println(lastMessage);
			out.flush();
		}
	}
}