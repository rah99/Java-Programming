package parallel;

class Th2 extends Thread {
	Table2 t;
	Th2(Table2 t) {
		this.t = t;
	}	
	public void run() {
		Table2.printTable(100);
	}
}
