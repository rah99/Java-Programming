package threadingDemo;

public class Threading extends Thread {
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getId());
	}
}
