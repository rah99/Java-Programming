package serverThreading;

public class Thread4 extends Thread {
	Server s;
	Thread4(Server s) {
		this.s = s;
	}

	public void run() {
		Server.serverTableSync(ServerMainSync.UserInput);
	}
}