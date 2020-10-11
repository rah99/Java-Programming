package mainThread;

public class MainThread {
	static long mainThreadId = Thread.currentThread().getId();
	
	public static boolean isMainThread() {
		return Thread.currentThread().getId() == mainThreadId;
	}
}
