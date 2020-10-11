package parallel;

class Table2 {
	static void printTable(int n) {
		for(int i = 1; i <= 5; i++) {
			System.out.println(Thread.currentThread().getName());
			System.out.println(n * i);
			//			System.out.println("Cheerio");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}
}
