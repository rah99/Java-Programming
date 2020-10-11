package parallel;

class Th1 extends Thread {
	Table1 t;
	Th1(Table1 t) {
		this.t = t;
	}
	public void run() {
		Table1.printTable(5); // Advised to use static method of calling class by actual name and not variable
	}
}
