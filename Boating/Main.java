package Boating;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		int boats = 10 + 1; // So as no boat 0
		int duration = 5000; // Equals 5 secs
		int timer = 800; // Equals .8 secs
		
		ExecutorService executor = Executors.newCachedThreadPool(); // Cached so as it can be used no mater the number of boats
		
		final Semaphore boatReturned = new Semaphore(boats, true); // Semaphore set to act as a a return thread for the number of boats
																// taken out/every thread spawned in the for loop
		
		for (int i = 1; i < boats; i++) { // For loop to spawn the threads as per the boats variable start at 1 so as no boat 0
			boatReturned.acquire();
			Thread.sleep(500);
			executor.execute(new BoatThread(i, duration));
			boatReturned.release(timer);
		}
		executor.shutdown();
	}

}
