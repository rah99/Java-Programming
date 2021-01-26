package Boating;

import java.util.concurrent.Semaphore;

public class InOut {
	
	private int boats = 10;
	
	// in() initialized with 0 permits
	// to ensure out() method executes first
	private  Semaphore in = new Semaphore(0);
	private Semaphore out = new Semaphore(1);

	public void out() {
		try {
			out.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.release();
	}
	
	public void in() {
		try {
			in.acquire();
		} catch (Exception e) {
			// TODO: handle exception
		}
		in.release();
	}
}
