package overloadingMonitorLock;

public class CleanHair {
	String currentState = ""; // The object state
//	private volatile boolean running = true;
	int num;
	int iter = 0;

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

//	public void stopNow() {
//		running = false;
//	}

	synchronized void Lather() {
		currentState = "Lather";
		System.out.println(currentState + " " + Thread.currentThread().getId());
		notify();
		while (currentState.equals("Lather")) {
			try {
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
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
