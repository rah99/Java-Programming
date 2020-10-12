package threadArrays;

public class ThreadRunnable implements Runnable {
	@Override
	public synchronized void run() {
		Thread t = Thread.currentThread();
		System.out.println("Howdy people " + t.getName());
		System.out.println("Laters people " + t.getName());
	}
}
