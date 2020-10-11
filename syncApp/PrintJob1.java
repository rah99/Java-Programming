package syncApp;

public class PrintJob1 extends Thread {

	Printer pRef;

	PrintJob1(Printer p1) {
		pRef = p1;
	}

	//	// **Method 1**
	//	@Override
	//	public void run() {
	//		pRef.printDocuments(10, "FasProfile.pdf");
	//	}

	// **Method 2** // Synchronising from the thread
	@Override
	public void run() {
		synchronized (pRef) {
			pRef.printDocuments(10, "FasProfile.pdf");
		}
	}
}
