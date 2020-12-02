package overloadingMonitorLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyExecServe {

	public static void main(String[] args) {
		ExecutorService executors = Executors.newFixedThreadPool(2);
		CleanHair clean = new CleanHair();

//		executors.execute(new HairThreadRunnable("Lather", clean)); //execute simply runs the task and can only be used for a runnable class
//		executors.execute(new HairThreadRunnable("Rinse", clean));
		
		executors.submit(new HairThreadRunnable("Lather", clean)); // submit runs the task and can be used to return a Future object for both runnable and callable classes
		executors.submit(new HairThreadRunnable("Rinse", clean));
	}
}
