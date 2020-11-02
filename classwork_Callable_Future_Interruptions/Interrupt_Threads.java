package classwork_Callable_Future_Interruptions;

import java.util.Random;

public class Interrupt_Threads {

	public static void main(String[] args) {
		System.out.println("Starting");
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				Random random = new Random();
				for (int i = 0; i < 1E8; i++) {
					Math.sin(random.nextDouble());
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted!");
						break;
					}
				}
			}
		});
		t1.start();
		try {
			Thread.sleep(500);
			t1.interrupt();
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished.");
	}

}
