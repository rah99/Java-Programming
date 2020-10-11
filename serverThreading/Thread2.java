package serverThreading;

public class Thread2 extends Thread {
	Server s;
	Thread2(Server s) {
		this.s = s;
	}

	public void run() {
		Server.serverTableSync(ServerMainSync.UserInput);
	}
}
