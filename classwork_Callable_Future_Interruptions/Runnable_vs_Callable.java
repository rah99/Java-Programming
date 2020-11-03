package classwork_Callable_Future_Interruptions;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Runnable_vs_Callable {

	//	**Runnable""

	//	public static void main(String[] args) {
	//		ExecutorService executor = Executors.newCachedThreadPool();
	//		executor.submit(new Runnable() {
	//
	//			@Override
	//			public void run() {
	//				Random random = new Random();
	//				int duration = random.nextInt(4000);
	//				System.out.println("Starting.....");
	//				try {
	//					Thread.sleep(duration);
	//				} catch (InterruptedException e) {
	//					e.printStackTrace();
	//				}
	//				System.out.println("Finished.");
	//			}
	//		});
	//	}

	//	**Callable**

	public static void main(String[] args) throws IOException {
		ExecutorService executor = Executors.newCachedThreadPool();
		Random random = new Random();
		int duration = random.nextInt(4000);
		
		System.out.println("Random duration is: " + duration);
		
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			//			public Integer call() throws Exception {
			//				return duration;
			//			}	
			public Integer call() {//throws IOException {
//				Random random = new Random();
//				int duration = random.nextInt(4000);
//				if(duration > 2000) {
//					throw new IOException("Sleeping to long");
//				}
				return duration;
			}
		});
			if (duration > 2000) {
				throw new IOException("Sleeping too long");
			}
//		try {
//			System.out.println("Result: " + future.get());
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
		executor.shutdown(); // https://docs.oracle.com/javase/10/docs/api/java/util/concurrent/ExecutorService.html
		try {
			System.out.println("Terminating services...");
			if (!executor.awaitTermination(3500, TimeUnit.MICROSECONDS)) {
				executor.shutdownNow();
			}
			if (!executor.awaitTermination(3500, TimeUnit.MICROSECONDS)) {
				System.err.println("Service did not terminate");
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
		System.out.println("Cleanup complete.");
	}
}
