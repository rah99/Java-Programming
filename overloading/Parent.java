package overloading;

import java.util.Scanner;

public class Parent {

	public static void main(String[] args) {
		Double miles;
		int times;
		String place;

		// Setup scanner
		Scanner walkScanner = new Scanner(System.in);

		// Get user input
		System.out.print("Enter how many miles you walked: ");	
		while (!walkScanner.hasNextDouble()) {
			System.out.print("Double values only e.g. 2 or 2.14. Please reenter your miles walked: ");
			walkScanner.nextLine(); // nextLine important if you do not want the above system out to replicate for every false entry in initial question
		}
		miles = walkScanner.nextDouble();

		System.out.print("Enter how many times you walked the same route: ");
		while (!walkScanner.hasNextInt()) {
			System.out.print("Integer values only e.g. 2. Please reenter your number of times: ");
			walkScanner.nextLine();
		}
		times = walkScanner.nextInt();

		System.out.print("Enter where you walked: ");
		place = walkScanner.next();
		place += walkScanner.nextLine(); // This is required as "walkScanner.next();" will only read the first complete part (first joined set of chars) string entered...
		// .. and the next.Int beforehand will cause an error if nextLine used in the first string reader as next keeps the cursor on the same line as the int declaration

		Child c = new Child();
		c.walk();
		c.walk(miles, times);
		c.walk(miles, times, place);

		// close the scanner
		System.out.println("Closing Scanner...");
		walkScanner.close();
		System.out.println("Scanner Closed.");
	}

	public void walk() {
		System.out.println("Hello");
	}
}

// System.out.print = the user input will be on the same line as the question
// System.out.println = the user input will be on a new line below the question
