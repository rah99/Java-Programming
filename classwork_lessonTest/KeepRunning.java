package classwork_lessonTest;

public class KeepRunning extends Thread {
	private volatile boolean running = true;
	//	private volatile boolean stopRunning = false; // volatile creates synchronisation - thus preventing two things happening at once
	//	public void run() {							// https://stackoverflow.com/questions/25425130/loop-doesnt-see-value-changed-by-other-thread-without-a-print-statement
	//		synchronized (this) {					// https://stackoverflow.com/questions/10961714/how-to-properly-stop-the-thread-in-java#:~:text=When%20you%20wish%20to%20stop%20the%20thread%2C%20you,with%20the%20variable%20being%20used%20as%20the%20flag.
	//			while (!stopRunning) {
	//				System.out.println("Hello");
	//				try {
	//					this.sleep(1500);
	//				} catch (InterruptedException e) {
	//					e.printStackTrace();
	//				}
	//				System.out.println("Going to sleep...");
	//			}
	//		}
	//		System.out.println("I have been stopped..");
	//	}

	public void run() {
		while (running) {
			System.out.println("Hello");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				stopRunning();
			}
		}
	}

	public void stopRunning() {
		running = false;
	}

	//	public boolean getStopRunning() {
	//		return stopRunning;
	//	}

	//	public void setStopRunning(boolean stopRunning) {
	//		this.stopRunning = stopRunning;
	//	}

	//	public void StopThread() {
	//		synchronized (this) {
	//			stopRunning = true;
	//			this.notifyAll();
	//		}
	//	}
	//}

	//void eatPizza() {
	//    synchronized (this) {
	//        while (!pizzaArrived) {
	//            try {
	//                this.wait();
	//            } catch (InterruptedException e) {}
	//        }
	//    }
	//
	//    System.out.println("That was delicious!");
}
