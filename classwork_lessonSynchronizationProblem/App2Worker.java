package classwork_lessonSynchronizationProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App2Worker {

	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	private Random random = new Random();
	private Object lock1 = new Object();
	private Object lock2 = new Object();

	public void stageOne() {
		try {
			Thread.sleep(1); // get re3mote information
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(lock1) {
			list1.add(random.nextInt(100));
		}
	}

	public void stageTwo() {
		try {
			Thread.sleep(1); // get re3mote information
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(lock2) {
			list2.add(random.nextInt(100));
		}

	}

	public void process() {
		for(int i = 0; i < 500; i++) {
			stageOne();
			stageTwo();
		}
	}

	public void main() {
		System.out.println("Start Thread: " + Thread.currentThread().getId());

		long start = System.currentTimeMillis();

		Thread t1 = new Thread (new Runnable() {
			public void run() {
				process();
			}
		});

		Thread t2 = new Thread (new Runnable() {
			public void run() {
				process();
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		String total = String.format("%02d sec", TimeUnit.MILLISECONDS.toSeconds(end - start));

		System.out.println("Time taken: " + total);
		System.out.println("List1: " + list1.size());
		System.out.println("List2: " + list2.size());
	}
}
