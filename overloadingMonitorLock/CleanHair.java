package overloadingMonitorLock;

public class CleanHair {
	String currentState = ""; // The object state

	//	synchronized void Wet() {
	//		currentState = "Wet";
	//		System.out.println(currentState);
	//		notify();
	//		try {
	//			while (currentState.equals("Wet")) { // Prevents spurious wake up as explained here https://stackoverflow.com/questions/8594591/why-does-pthread-cond-wait-have-spurious-wakeups/8594644#8594644
	//				wait();
	//			}
	//		} catch (InterruptedException e) {
	//			e.printStackTrace();
	//		}
	//	}

	//	synchronized void Shampoo() {
	//		currentState = "Shampoo";
	//		System.out.println(currentState);
	//		notify();
	//		try {
	//			while (currentState.equals("Shampoo")) {
	//				wait();
	//			}
	//		} catch (InterruptedException e) {
	//			e.printStackTrace();
	//		}
	//	}

	synchronized void Lather() {
		currentState = "Lather";
		System.out.println(currentState + " " + Thread.currentThread().getId());
		notify();
		try {
			while (currentState.equals("Lather")) {
				wait();
				//				Thread.currentThread().interrupt();
			}
		} catch (InterruptedException e) {
			//			e.printStackTrace();
			//			throw new RuntimeException("Iterrupted", e);
		}
	}

	synchronized void Rinse() {
		currentState = "Rinse";
		System.out.println(currentState + " " + Thread.currentThread().getId());
		notify();
		try {
			while (currentState.equals("Rinse")) {
				wait();
				//				Thread.interrupted();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//	synchronized void Dry() {
	//		currentState = "Dry";
	//		System.out.println(currentState);
	//		notify();
	//		try {
	//			while (currentState.equals("Dry")) {
	//				wait();
	//			}
	//		} catch (InterruptedException e) {
	//			e.printStackTrace();
	//		}
	//	}
}
