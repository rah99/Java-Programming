package synchronisation;

public class Th1 extends Thread {
	Table t;
	Th1(Table t) {
		this.t = t;
	}
	public void run() {
		Table.printTable(5); // Advised to use static method of calling class by actual name and not variable
	}
}
