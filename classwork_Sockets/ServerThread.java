package classwork_Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket;
	
	ServerThread(Socket socket) {
		this.socket = socket;
	}
	public void run() {
		String message = null;
		BufferedReader bufferedReader = null;
		
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			while ((message = bufferedReader.readLine()) != null) {
				System.out.println("Incoming client message " + message);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
