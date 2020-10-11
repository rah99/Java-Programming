package serverThreading;

public class Thread1Parallel extends Thread {
	Server sParallel;
	Thread1Parallel(Server sParallel) {
		this.sParallel = sParallel;
	}

	public void run() {
		Server.serverTableParallel(ServerMainParallel.UserInput);
	}
}
