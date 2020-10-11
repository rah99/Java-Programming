package overloadingMonitorLock;

public class HairThread extends Thread {
	private volatile boolean stopNow = false;
	String name;
	CleanHair cleanRef;

	public void stopNow (boolean stopNow) {
		this.stopNow = stopNow;
	}

	public void getStopNow () {
		this.stopNow = stopNow;
	}

	public HairThread(String name, CleanHair cleanRef) {
		this.name = name;
		this.cleanRef = cleanRef;
		setName(name);
		start();
		try {
			sleep(1);
			//			if (isAlive()) {
			//				stopNow(true);
			//			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
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
			for (int i = 0; i < WaitNotify.UserInput; i++) {
				//				if (!stopNow && i < WaitNotify.UserInput) {
				cleanRef.Lather();
				//				} else {
				//					stopNow(true);
				//				}
				//				if (stopNow) {
				//					break;
				//				}

			}
		}
		if (name.equals("Rinse")) {
			//				while(true) { cleanRef.Rinse(); }
			for (int i = 0; i < WaitNotify.UserInput; i++) {
				//				if (!stopNow && i < WaitNotify.UserInput) {
				cleanRef.Rinse();
				//				} else {
				//					stopNow(true);
				//				}
				//				if (stopNow) {
				//					break;
				//				}
			}
		}
		//		if (name.equals("Dry")) {
		////			while(true) { cleanRef.Dry(); }
		//			for (int i = 0; i < WaitNotify.UserInput; i++) {
		//				cleanRef.Dry();
		//			}
		//		}
	}
}
