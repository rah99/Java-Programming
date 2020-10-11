package syncApp;

public class PrintJob2 extends Thread {

	Printer pRef;

	PrintJob2(Printer p2) {
		pRef = p2;
	}

	//	// **Method 1**
	//	@Override
	//	public void run() {
	//		pRef.printDocuments(10, "JohnsProfile.pdf");
	//	}

	// **Method 2** // Synchronising from the thread
	@Override
	public void run() {
		synchronized (pRef) {
			pRef.printDocuments(10, "TimProfile.pdf");
		}
	}
}