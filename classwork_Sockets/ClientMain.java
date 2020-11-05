package classwork_Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {

	public static void main(String[] args) {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("What's your name: ");
		try {
			String name = bufferedReader.readLine();
			String message;
			Socket socket = new Socket("127.0.0.1", 4444);
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader serverBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.print("Write a message: ");
			
			while (true) {
				String readerInput = bufferedReader.readLine();
				printWriter.println("from " + name + " saying " + readerInput);
				message = serverBufferedReader.readLine();
				System.out.println(message);
				socket.close(); // Should this be here as not in James's code
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
