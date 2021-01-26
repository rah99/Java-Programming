package footballGround;

public class Turnstile extends Thread {
	private Counter people;

	public Turnstile(Counter people) {
		this.people = people;
	}
	public void run() {	
		while (true) {
			// simulate people arriving every half-second
			try { sleep(500); } catch (InterruptedException ex) {}
			people.increment();
//			System.out.println(people.toString() + Thread.currentThread().getId());
		}
	}
}

