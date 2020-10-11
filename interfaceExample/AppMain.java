package interfaceExample;

import mainThread.MainThread;

public class AppMain {
	
	// Main method represents main thread
	public static void main(String[] args) {
		// Everything coded here will be executed in the main thread
		// Threads execute (Execution Context) jobs in a sequence, as such Job 1 needs to complete before Job 2 can start and so on. In the case where...
		// ..Job 2, for instance, is a large job then the queued jobs will generate a "NOT RESPONDING* message from the OS/JVM (Java Virtual Machine) resulting... 
		// ..in the App seeming to hang if using "Process 1" of Task1 class
		// When using "Process 2" of Task1 class the App runs in parallel or concurrently (depending on the cores being utilised)
		
		// Job 1
		System.out.println("**Application Started**");
		
		// Job 2 - Child/Worker Thread
//		Task1 ta1 = new Task1();
////		ta1.executeTask();
//		ta1.start();
		Runnable r = new Task1(); // Example of polymorphic statement - reference variable is pointing to the object of the class that implements it
		Thread ta1 = new Thread(r);
		ta1.start();
		
		// Job 3
//		Thread ta2 = new Thread(new Task2()); // This can be written as below
//		ta2.start();
		new Thread(new Task2()).start();
		try {
			ta1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainThread.isMainThread();
		// Job 3
		// For loop = Code to print documents
		for(int doc = 1; doc <= 10; doc++) {
			System.out.println("^^ Printing document # " + doc + " - Printer 1");
		}
		
		// Job 4
		System.out.println("**Application Finished**");
	}
}
// **Resources**
// https://www.youtube.com/watch?v=TCd8QIS-2KI&t=813s
