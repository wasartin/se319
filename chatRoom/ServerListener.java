package hw1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerListener implements Runnable {
	Client c;
	Scanner in; 

	ServerListener(Client c, Socket s) {
		try {
			this.c = c;
			in = new Scanner(new BufferedInputStream(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		while (true) {
			String cmd = in.next();
			String s = in.nextLine();
			c.handleMessage(cmd, s);
		}
	}
}