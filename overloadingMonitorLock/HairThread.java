package overloadingMonitorLock;

public class HairThread extends Thread {
	String name;
	CleanHair cleanRef;

	public HairThread(String name, CleanHair cleanRef) {
		this.name = name;
		this.cleanRef = cleanRef;
		setName(name);
		start();
	}

	@Override
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
