package synchronisation;

public class Th2 extends Thread {
	Table t; // Synchronised method from 1 table, if needed in parallel comment section 1 and uncomment section 2
	Th2(Table t) {
		this.t = t;
	}
	public void run() {
		Table.printTable(100);
	}
}
