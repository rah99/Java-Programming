package overloadingMonitorLock;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyExecServe {

	public static void main(String[] args) {
		int num = 0;
		String choice;
		Scanner scanner = new Scanner(System.in);
		ExecutorService executors = Executors.newFixedThreadPool(2);
		CleanHair clean = new CleanHair();
		
		CountDownLatch latch = new CountDownLatch(2);
		executors.submit(new HairThreadRunnable(latch));
		executors.submit(new HairThreadRunnable(latch));

		do {
			do {
				System.out.print("Please enter the number of times you would like to run the threads: ");
				while (!scanner.hasNextInt()) {
					scanner.nextLine();
					System.out.print("Whole numbers only, please enter a number: ");
				}
				num = scanner.nextInt();
				scanner.nextLine();
				System.out.println("You entered " + num);
				System.out.print("Would you like to continue - Y to continue, N to change the iterations: ");
				while (!scanner.hasNext("[/yn|YN/]")) { // This validation may be better as a switch statement - regex not the easiest to work out (this case is simple)
					scanner.nextLine();
					System.out.print("Y or N only, please enter your choice: ");
				}
				choice = scanner.next();
			} while (choice.equalsIgnoreCase("N"));

				executors.submit(new HairThreadRunnable("Lather", clean, num));
				executors.submit(new HairThreadRunnable("Rinse", clean, num));
				
				try {
					latch.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			System.out.print("Would you like to run the program again? (Y/N): ");
			while (!scanner.hasNext("[/yn|YN/]")) {
				scanner.nextLine();
				System.out.print("Y or N only, please enter your choice: ");
			}
			choice = scanner.next();
		} while (choice.equalsIgnoreCase("Y"));
		scanner.close();
		System.exit(0);
	}

	public interface ThreadCompleteListener {
		void notifyOfThreadComplete(final Thread thread);
	}
}
