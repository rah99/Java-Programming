package classwork_lessonWaitNotify;

import java.util.Random;

public class LowLevelProcessor {
	private int counter = 0;
	private final int LIMIT = 10;
	private Object lock = new Object();
	
	public void producer() throws InterruptedException {
		while(true) {
			synchronized (lock) {
				while(counter >= LIMIT) {
					lock.wait();
				}
				counter++;
				System.out.println("Add to list - " + counter);
				lock.notify();
			}
		}
	}
	
	public void consumer() throws InterruptedException {
		Random random = new Random();
		while(true) {
			synchronized (lock) {
				while(counter == 0) {
					lock.wait();
					Thread.sleep(500);
				}
				counter--;
				System.out.println("Remove from list - " + counter);
				lock.notify();
			}
			Thread.sleep(random.nextInt(1));
		}
	}
}
