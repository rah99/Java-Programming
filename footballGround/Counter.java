package footballGround;

public class Counter {
	private int count = 0;

	public synchronized void increment() {
		++count;
		System.out.println("Count is " + count + " on thread " + Thread.currentThread().getId());
	}
}

