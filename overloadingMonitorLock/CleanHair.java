package overloadingMonitorLock;

public class CleanHair {
	String currentState = ""; // The object state
	private volatile boolean running = true;
	int num;
	int iter = 0;
	
	public int getNum() {
		return this.num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public void stopNow() {
		running = false;
	}

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
			while (currentState.equals("Lather") && running) {
				wait();
				Thread.interrupted();
			}
			//				Thread.currentThread().interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
			stopNow();
			//			throw new RuntimeException("Iterrupted", e);
		}
	}

	synchronized void Rinse() {
		currentState = "Rinse";
		System.out.println(currentState + " " + Thread.currentThread().getId());
		notify();
		try {
			while (currentState.equals("Rinse") && running) {
				wait();
				Thread.interrupted();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			stopNow();
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
