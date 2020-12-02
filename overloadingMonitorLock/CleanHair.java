package overloadingMonitorLock;

import java.time.Duration;
import java.time.LocalTime;

public class CleanHair {
	String currentState = ""; // The object state
	LocalTime start; // These variables are only to display that the time between execution is per the brief i.e. 2 seconds
	LocalTime end; // src = https://www.baeldung.com/java-period-duration
	long duration;

	synchronized void Lather() {
		currentState = "Lather";
		System.out.println(currentState + " " + Thread.currentThread().getId());
		notify(); // once the thread has printed the above it will send a notification that it has completed it's task so as any other thread waiting knows to activate - code below in the try/catch block then tells this thread to wait
		while (currentState.equals("Lather")) {
			try {
				start = LocalTime.now(); // setting variable "start" value
				Thread.sleep(2000); // tells the thread how long to be inactive
				end = LocalTime.now(); // setting variable "end" value
				duration = Duration.between(start, end).getSeconds(); // setting variable "duration" value
				System.out.println(duration + " seconds waited."); // printing the duration of the wait period to verify the thread waits as per the brief - would be omitted if in actual use
				wait(); // thread will wait until it is notified
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	synchronized void Rinse() {
		currentState = "Rinse";
		System.out.println(currentState + " " + Thread.currentThread().getId());
		notify();
		while (currentState.equals("Rinse")) {
			try {
				start = LocalTime.now();
				Thread.sleep(2000);
				end = LocalTime.now();
				duration = Duration.between(start, end).getSeconds();
				System.out.println(duration + " seconds waited.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
