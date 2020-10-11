package serverThreading;

public class Thread1 extends Thread {
	Server s;
	Thread1(Server s) {
		this.s = s;
	}

	public void run() {
		Server.serverTableSync(ServerMainSync.UserInput);
	}
}
