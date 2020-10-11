package serverThreading;

public class Thread4Parallel extends Thread {
	Server sParallel;
	public Thread4Parallel(Server sParallel) {
		this.sParallel = sParallel;
	}

	public void run() {
		Server.serverTableParallel(ServerMainParallel.UserInput);
	}
}
