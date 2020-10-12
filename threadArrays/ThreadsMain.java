package threadArrays;

import java.util.Scanner;

public class ThreadsMain {
	
	private static int arrNum = 10;

	public static void main(String[] args) throws InterruptedException {
		int num = 0;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter how many threads you would like in the array: ");
		while (!scanner.hasNextInt()) {
			scanner.nextLine();
			System.out.println("Whole numbers only, please reenter your number of threads: ");
		}
		num = scanner.nextInt();
		arrNum = num;
		scanner.nextLine();
		System.out.println("You entered: " + num);
		System.out.println("Press enter to continue");
		scanner.nextLine();
		
		Runnable hwodyObj = new ThreadRunnable();

		Thread []thread = new Thread[arrNum];
		for (int i = 0; i < arrNum; i++) {
			thread[i] = new Thread(hwodyObj, i + " ");
			thread[i].start();
		}
		for (int i = 0; i < arrNum; i++) {
			thread[i].join();
		}
		
		scanner.close();
	}
}
// https://www.youtube.com/watch?v=nuMHF6M_FdA