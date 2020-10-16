package classwork_lessonDeadLock;

public class Deadlock {
	private int counter = 0;
	private Object lock = new Object();

	public void method1() throws InterruptedException {
		while (true) {
			synchronized (lock) {
				while (counter > 1) {
					lock.wait();
					System.out.println("Waiting in method 1 " + "counter = " + counter);
				}
				counter++;
				System.out.println("Increase M1 " + "counter = " + counter);
				lock.notify();		
			}
		}
	}

	public void method2() throws InterruptedException {
		while (true) {
			synchronized (lock) {
				while (counter > 2) {
					lock.wait();
					System.out.println("Waiting in method 2 " + "counter = " + counter);
				}
				counter++;
				System.out.println("Increase M2 " + "counter = " + counter);
				lock.notifyAll(); // Without notifyAll() the threads deadlock - only need one notifyAll from the thread that is still alive
			}
		}
	}

	public void method3() throws InterruptedException {
		while (true) {
			synchronized (lock) {
				while (counter < 3) {
					lock.wait();
					System.out.println("Waiting in method 3 " + "counter = " + counter);
				}
				counter = 0;
				System.out.println("Zero " + "counter = " + counter);
				lock.notify();
			}
		}
	}
}
