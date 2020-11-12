package overloadingMonitorLock;

public class CleanHair {
	String currentState = ""; // The object state

	synchronized void Lather() {
		currentState = "Lather";
		System.out.println(currentState + " " + Thread.currentThread().getId());
		notify();
		while (currentState.equals("Lather")) {
			try {
				Thread.sleep(2000);
				wait();	
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
				Thread.sleep(2000);
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
