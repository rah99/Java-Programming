package overloadingMonitorLock;

public class WaitNotify {

	public static void main(String[] args) {

			CleanHair clean = new CleanHair(); // Single object
			new HairThread("Lather", clean);		
			new HairThread("Rinse", clean);
		}
}
