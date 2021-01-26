package footballGround;

public class Ground {
	public static void main(String[] args) throws InterruptedException {
		Counter people = new Counter();
		Thread homeEntrance = new Turnstile(people);
		Thread awayEntrance = new Turnstile(people);

		homeEntrance.start();
		awayEntrance.start();
	}
}

