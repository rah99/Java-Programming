package threadingDemo;

import java.time.Duration;
import java.time.Instant;

public class Threads {

	public static void main(String[] args) {	
		//		Instant startInstant = Instant.now();
		//		Threading object = new Threading();
		//		object.start();
		//		
		//		Thread object1 = new Thread(new TheadingRunnable());
		//		object1.start();
		//		Instant startInstant = Instant.now();
		Thread object2 = new Thread(new ServerThread("First"));
		object2.start();

		//		Instant endInstant = Instant.now();
		//		System.out.println(Duration.between(startInstant, endInstant).getSeconds());
	}
}

// **Resources**
// Timer solution - https://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
