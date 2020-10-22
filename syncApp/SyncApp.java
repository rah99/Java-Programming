package syncApp;

//We use synchronisation where two or more threads are working on the same object - creating a Thread Pool of queued Thread tasks

public class SyncApp {

	// This class represents the main thread
	public static void main(String[] args) {
		// Job 1
		System.out.println("**Application Started**");

		// Job 2
		// This method creates 1 single object of printer
		Printer printer = new Printer();
		printer.setTest(0); // 
		//		printer.printDocuments(10, "RobsProfile.pdf");

		// This method assigns the printer object to the thread running asynchronously aka in parallel, which for this scenario is incorrect...
		// ..as it needs to run synchronised so as one job completes then the other starts - needs a join to create this
		PrintJob1 pj1 = new PrintJob1(printer);
		pj1.setName("Admin Printer:");
		PrintJob2 pj2 = new PrintJob2(printer);
		pj2.setName("Sales Printer:");
		pj1.start();
		//		try { // A join is not practical if lots of printers, therefore making the "Printer" class "synchronised" will perform the join function or "Intrinsic Lock"
		//			pj1.join();
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		pj2.start();
		
		try { // join makes syso last operation
			pj2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Job 3
		System.out.println("**Application Finished**");
	}
}
