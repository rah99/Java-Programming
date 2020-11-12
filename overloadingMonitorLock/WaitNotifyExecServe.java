package overloadingMonitorLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyExecServe {

	public static void main(String[] args) {
		ExecutorService executors = Executors.newFixedThreadPool(2);
		CleanHair clean = new CleanHair();

		executors.execute(new HairThreadRunnable("Lather", clean));
		executors.execute(new HairThreadRunnable("Rinse", clean));
	}
}
