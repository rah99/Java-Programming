package Boating;

//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		
//		int boats = 10 + 1; // So as no boat 0
//		int duration = 5000; // Equals 5 secs
//		int timer = 800; // Equals .8 secs
		
		InOut boatControl = new InOut();
		
		new BoatIn(boatControl);
		new BoatOut(boatControl);
		
//		ExecutorService executor = Executors.newCachedThreadPool(); // Cached so as it can be used no mater the number of boats
//		
//		ExecutorService executor = Executors.newCachedThreadPool();
//		
//		executor.execute(new BoatIn(boatControl));
//		executor.execute(new BoatOut(boatControl));
//		
//		final Semaphore boatReturned = new Semaphore(boats, true); // Semaphore set to act as a a return thread for the number of boats
//																// taken out/every thread spawned in the for loop
//		
//		for (int i = 1; i < boats; i++) { // For loop to spawn the threads as per the boats variable start at 1 so as no boat 0
//			Thread.sleep(500);
//			executor.execute(new BoatOut(i, duration));
//		}
//		executor.shutdown();
	}

}

//Resource for fix https://www.youtube.com/watch?v=y_IkzDVb1Dc
