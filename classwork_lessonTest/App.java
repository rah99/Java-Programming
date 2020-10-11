package classwork_lessonTest;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		KeepRunning krun = new KeepRunning();
		krun.start();
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();

		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		krun.stopRunning();

		scanner.close();
	}
}
