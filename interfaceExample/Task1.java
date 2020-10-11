package interfaceExample;

////**Process 1**
//class Task1 {
//	void executeTask() {
//		for(int doc = 1; doc <= 10; doc++) {
//			System.out.println("@@ Printing document # " + doc + " - Printer2");
//		}
//	}
//}

////**Process 2**
////This makes the task a thread
//class Task1 extends Thread {
//	@Override
//	public void run() {
//		for(int doc = 1; doc <= 10; doc++) {
//			System.out.println("@@ Printing document # " + doc + " - Printer2");
//		}
//	}
//}

//**Process 3**
//class Task1 extends CA, Thread { -> Creates Error as multiple inheritance is not supported in Java
class Task1 extends CA implements Runnable {
	@Override
	public void run() {
		for(int doc = 1; doc <= 10; doc++) {
			System.out.println("@@ Printing document # " + doc + " - Printer 2");
		}
	}
}