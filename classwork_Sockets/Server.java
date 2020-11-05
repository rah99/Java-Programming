package classwork_Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public void runServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(4444);
		System.out.println("Server up and ready for connections...");
		while (true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();
		}
	}
}
