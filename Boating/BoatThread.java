package Boating;

import java.util.concurrent.ThreadLocalRandom;

public class BoatThread implements Runnable {

	private int num;
	private int duration;
	
	public BoatThread(int num, int duration) {
		super();
		this.num = num;
		this.duration = duration;
	}

	@Override
	public void run() {
		try {
			System.out.println("Boat " + num + " is taken out"); // Output
			Thread.sleep(ThreadLocalRandom.current().nextInt(duration)*5); // Java class ThreadLocalRandom replaces the Random call for the
																		// duration variables value so as all boats are taken before any returned
			System.out.println("Boat " + num + " is returned");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
