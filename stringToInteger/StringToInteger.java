package stringToInteger;

import java.util.Scanner;

public class StringToInteger {

	public static void main(String[] args) {
		// Create scanner for user input
		Scanner stringScanner = new Scanner(System.in);
		String s;
		System.out.print("Please enter your age : ");
		s = "Your age is: " + stringScanner.next();
		System.out.println(s);

		s = s.replaceAll("\\D+", ""); // Regular expression where the D = digit thus removes everything other than the digits;

		int n = Integer.parseInt(s); // Parse the string to integer

		System.out.println("In two years you will be: " + (n + 2));

		// close the scanner
		System.out.println("Closing Scanner...");
		stringScanner.close();
		System.out.println("Scanner Closed.");
	}
}

// **Resource**
// https://www.youtube.com/watch?v=Pnaqn6GOyzU
