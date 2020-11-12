package overloadingMonitorLock;

public class HairThreadRunnable implements Runnable {
	String name;
	CleanHair cleanRef;
	int num;

	public HairThreadRunnable(String name, CleanHair cleanRef) {
		this.name = name;
		this.cleanRef = cleanRef;
	}

	public void run() {	
		if (name.equals("Lather")) {
			while (true) {
				cleanRef.Lather();
			}	
		}

		if (name.equals("Rinse")) {
			while (true) {
				cleanRef.Rinse();
			}	
		}
	}
}
