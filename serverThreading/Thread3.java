package serverThreading;

public class Thread3 extends Thread {
	Server s;
	Thread3(Server s) {
		this.s = s;
	}

	public void run() {
		Server.serverTableSync(ServerMainSync.UserInput);
	}
}
