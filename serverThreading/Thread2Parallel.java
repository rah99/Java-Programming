package serverThreading;

public class Thread2Parallel extends Thread {
	Server sParallel;
	Thread2Parallel(Server sParallel) {
		this.sParallel = sParallel;
	}

	public void run() {
		Server.serverTableParallel(ServerMainParallel.UserInput);
	}
}
