package serverThreading;

class MainThread extends Thread {
	static long mainThreadId = Thread.currentThread().getId();

	public static boolean isMainThread() {
		return Thread.currentThread().getId() == mainThreadId;
	}

	ServerMainParallel mtParallel;
	MainThread(ServerMainParallel mtParallel) {
		this.mtParallel = mtParallel;
	}

	public void run() {

	}
}
