package serverThreading;

public class Thread3Parallel extends Thread {
	Server sParallel;
	Thread3Parallel(Server sParallel) {
		this.sParallel = sParallel;
	}

	public void run() {
		Server.serverTableParallel(ServerMainParallel.UserInput);
	}
}
