package Boating;

public class BoatIn implements Runnable {
	
	InOut inOut;
	
	public BoatIn(InOut inOut) {
		this.inOut = inOut;
		new Thread(this, "BoatIn").start();
	}

	@Override
	public void run() {
		for (int i = 1; i < 11; i++) inOut.in();
	}

}
