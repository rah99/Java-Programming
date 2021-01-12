package classwork_Callable_Future_Interruptions;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class James_Code {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				/*
		if (duration > 2000) {
		throw new IOException("Sleeping Too Long");
		}*/
				System.out.println("Starting ... ");
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Finished.");
				return duration / 1000;
			}
		});
		executor.shutdown();
		try {
			System.out.println("Result: " + future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}

