package synchronisation;

public class Table {
	synchronized static void printTable(int n) {
		for(int i = 1; i <= 5; i++) {
			System.out.println(Thread.currentThread().getName());
			System.out.println(n * i);
			//			System.out.println("Hello");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}
}
//	**Resources**
// https://www.javatpoint.com/
// https://knpcode.com/java-programs/get-current-thread-name-and-id-in-java/
