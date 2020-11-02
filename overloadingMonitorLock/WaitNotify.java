package overloadingMonitorLock;

import java.util.Scanner;

import mainThread.MainThread;

public class WaitNotify {

//	public volatile static int UserInput = 0;

	public static void main(String[] args) {

		int num = 0;
		String choice;
		Scanner scanner = new Scanner(System.in);
		Thread mth = Thread.currentThread();
		System.out.println("I am the main thread and my ID is: " +mth.getId());

		do {
			do {
				System.out.print("Please enter the number of times you would like to run the threads: ");
				while (!scanner.hasNextInt()) {
					scanner.nextLine();
					System.out.print("Whole numbers only, please enter a number: ");
				}
				num = scanner.nextInt();
//				UserInput = num; // Here as num has 0 value until this point
				scanner.nextLine();

				System.out.println("You entered " + num);
				//			System.out.println("Press enter to continue");
				//			scanner.nextLine();
				System.out.print("Would you like to continue - Y to continue, N to change the iterations: ");
				while (!scanner.hasNext("[/yn|YN/]")) { // This validation may be better as a switch statement - regex not the easiest to work out (this case is simple)
					scanner.nextLine();
					System.out.print("Y or N only, please enter your choice: ");
				}
				choice = scanner.next();
			} while (choice.equalsIgnoreCase("N"));

			CleanHair clean = new CleanHair(); // Single object
			//		new HairThread("Wet", clean);
			//		new HairThread("Shampoo", clean);
			new HairThread("Lather", clean, num);		
			new HairThread("Rinse", clean, num);
			//		new HairThread("Dry", clean);

			//			try {
			//				Thread.sleep(3000);
			//				if(HairThread.currentThread().isAlive()) {
			//					Thread.currentThread().set;
			//				}
			//			} catch (InterruptedException e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
			//			try {
			//				Thread.currentThread().join();
			//			} catch (InterruptedException e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
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
}

// Try this to solve thread hang at end of program https://stackoverflow.com/questions/16618113/how-to-make-the-main-end-last also look at...
// ..ExecutorService
