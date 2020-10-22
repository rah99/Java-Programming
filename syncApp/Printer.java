package syncApp;

public class Printer {
	private int test = 0;
	//	synchronized void printDocuments(int numOfCopies, String docName) { // This can be performed at the thread level - refer individual threads to see this
	void printDocuments(int numOfCopies, String docName) {
		for(int i = 1; i <= numOfCopies; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " Printing " + docName + " " + i);
		}
	}
	
	public int getTest() {
		return this.test;
	}
	
	public void setTest(int test) {
		this.test = test;
	}
}
