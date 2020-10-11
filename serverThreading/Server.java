package serverThreading;

import java.time.Duration;
import java.time.Instant;

class Server {
	static void serverTableParallel(int vMaxAccounts) {
		Instant startInstant = Instant.now();

		for(int x = 0; x <= vMaxAccounts; x++) {
			System.out.println(Thread.currentThread().getName());			
			//			try { // Use to demonstrate parallel
			//				Thread.sleep(1500);
			//			} catch (Exception e) {
			//				System.out.println("Error: " + e);
			//			}
		}
		Instant endInstant = Instant.now();
		System.out.println(Duration.between(startInstant, endInstant));
	}

	synchronized static void serverTableSync(int vMaxAccounts) {
		Instant startInstant = Instant.now();

		for(int x = 0; x <= vMaxAccounts; x++) {
			System.out.println(Thread.currentThread().getName());
		}
		Instant endInstant = Instant.now();
		System.out.println(Duration.between(startInstant, endInstant));

		//		try { // Use to demonstrate synchronisation
		//			Thread.sleep(1500);
		//		} catch (Exception e) {
		//			System.out.println("Error: " + e);
		//		}
	}
}
