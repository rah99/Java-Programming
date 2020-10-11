package threadingDemo;

import java.time.Duration;
import java.time.Instant;

public class ServerThread implements Runnable {
	String name;

	public ServerThread(String name) {
		this.name = name;
	}

	public void run() {
		//		long start = System.nanoTime();
		Instant startInstant = Instant.now();
		int vMaxAccounts = 5000;
		for(int x = 0; x <= vMaxAccounts; x++) {
			for(int y = 0; y <= vMaxAccounts; y++) {
				//for(int z = 0; z <= vMaxAccounts; z++) {
				//for(int j = 0; j <= vMaxAccounts; j++) {
				System.out.println(Thread.currentThread().getName());
			}
		}
		//}
		//}
		//		long end = System.nanoTime();
		//		long exec = end - start;
		//		double inSeconds = (double)exec / 1000000000.0;
		Instant endInstant = Instant.now();
		////		
		System.out.println(Duration.between(startInstant, endInstant));
		//		System.out.println("The program takes " + exec + " nanoseconds which is " + inSeconds + " seconds to execute.");
	}
}