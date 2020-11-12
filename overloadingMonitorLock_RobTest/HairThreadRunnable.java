package overloadingMonitorLock_RobTest;

import java.util.concurrent.CountDownLatch;

public class HairThreadRunnable implements Runnable {
	private volatile boolean running = true;
	String name;
	CleanHair cleanRef;
	int num;
	
	private CountDownLatch latch;
	
	public HairThreadRunnable(CountDownLatch latch) {
		this.latch = latch;
	}

	public void stopNow() {
		running = false;
	}

	public HairThreadRunnable(String name, CleanHair cleanRef, int num) {
		this.name = name;
		this.cleanRef = cleanRef;
		this.num = num;
	}

	public void run() {
		
		if (name.equals("Lather")) {
			for (int i = 0; i < num; i++) {
				cleanRef.Lather();
			}
		}latch.countDown();

		if (name.equals("Rinse")) {
			
			for (int i = 0; i < num; i++) {
				cleanRef.Rinse();
			}
		}latch.countDown();
	}
}
