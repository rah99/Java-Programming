package overloadingMonitorLock;

public class HairThread extends Thread {
	private volatile boolean running = true;
	String name;
	CleanHair cleanRef;
	int num;
//	int iter = 0;
	
	public void stopNow() {
		running = false;
	}

	public HairThread(String name, CleanHair cleanRef, int num) {
		this.name = name;
		this.cleanRef = cleanRef;
		this.num = num;
		setName(name);
		start();
		try {
			sleep(1000);
			//			if (isAlive()) {
			//				stopNow(true);
			//			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if (running && iter < num) {
//			try {
//				wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		} else {
//			Thread.interrupted();
//			stopNow();
//		}
	}

	@Override
	public void run() {

		//		if (name.equals("Wet")) {
		////			while(true) { cleanRef.Wet(); } // While unlocks the intrinsic lock caused by the wait() notify()
		//			for (int i = 0; i < WaitNotify.UserInput; i++) {
		//				cleanRef.Wet();
		//			}
		//		}
		//		if (name.equals("Shampoo")) {
		////			while(true) { cleanRef.Shampoo(); }
		//			for (int i = 0; i < WaitNotify.UserInput; i++) {
		//				cleanRef.Shampoo();
		//			}
		//		}
		if (name.equals("Lather")) {
			//				while(true) { cleanRef.Lather(); }
			for (int i = 0; i < num; i++) {
				//				if (!stopNow && i < WaitNotify.UserInput) {
				cleanRef.Lather();
				if (Thread.currentThread().isInterrupted()) {
					break;
				}
			}
			//				} else {
			//					stopNow(true);
			//				}
			//				if (stopNow) {
			//					break;
			//				}

		}
		if (name.equals("Rinse")) {
			//				while(true) { cleanRef.Rinse(); }

			for (int i = 0; i < num; i++) {
				//				if (!stopNow && i < WaitNotify.UserInput) {
				cleanRef.Rinse();
				if (Thread.currentThread().isInterrupted()) {
					break;
				}
			}
			//				} else {
			//					stopNow(true);
			//				}
			//				if (stopNow) {
			//					break;
			//				}
		}
		//		if (name.equals("Dry")) {
		////			while(true) { cleanRef.Dry(); }
		//			for (int i = 0; i < WaitNotify.UserInput; i++) {
		//				cleanRef.Dry();
		//			}
		//		}
	}
}
