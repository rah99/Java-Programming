package serverThreading;

import java.util.Scanner;

public class ServerMainParallel {

	public static int UserInput = 0;

	public static void main(String[] args) {

		int num = 0;
		String choice;
		Scanner scanner = new Scanner(System.in);

		do {
			do {
				System.out.print("Please enter the number of times you would like to run the threads: ");
				while (!scanner.hasNextInt()) {
					scanner.nextLine();
					System.out.print("Whole numbers only, please enter a number: ");
				}
				num = scanner.nextInt();
				UserInput = num; // Here as num has 0 value until this point
				scanner.nextLine();

				System.out.println("You entered " + num);
				//			System.out.println("Press enter to continue");
				//			scanner.nextLine();
				System.out.print("Would you like to continue - Y to continue, N to change the iterations: ");
				while (!scanner.hasNext("[/yn|YN/]")) { // This validation may be better as a char variable then possibly no regex would be needed
					scanner.nextLine();
					System.out.print("Y or N only, please enter your choice: ");
				}
				choice = scanner.next();
			} while (choice.equalsIgnoreCase("N"));

			ServerQueries.parallelQuery();

			System.out.print("Would you like to run the program again? (Y/N): ");
			while (!scanner.hasNext("[/yn|YN/]")) {
				scanner.nextLine();
				System.out.print("Y or N only, please enter your choice: ");
			}
			choice = scanner.next();
		} while (choice.equalsIgnoreCase("Y"));
		scanner.close();
	}
}
