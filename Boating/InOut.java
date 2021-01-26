package Boating;

import java.util.concurrent.Semaphore;

public class InOut {
	
	int boat;
	
	// in() initialized with 0 permits so as it does not try take something that is not there
	// and ensures out() method executes first
	private Semaphore in = new Semaphore(0);
	private Semaphore out = new Semaphore(1);
	
	public void in() {
		try {
			Thread.sleep(1000);
			in.acquire();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Boat " + boat + " is returned");
		out.release();
	}

	public void out(int boat) {
		try {
			out.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.boat = boat;
		System.out.println("Boat " + boat + " is taken out");
		in.release();
	}
}
