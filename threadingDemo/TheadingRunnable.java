package threadingDemo;

class TheadingRunnable implements Runnable {
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getId());
	}
}
