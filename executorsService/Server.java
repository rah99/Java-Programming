package executorsService;

public class Server implements Runnable {

	@Override
	synchronized public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}
