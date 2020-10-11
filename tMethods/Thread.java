package tMethods;

class Th1 extends Thread {
	public void run() {
		//Thread.currentThread();
		//Thread t = Thread.currentThread();
		//t.setPriority(MAX_PRIORITY);
		//System.out.println("Thread status: " + t.isAlive());
		for(int i = 1; i <= 5; i++) {
			try {
				//System.out.println("ID = " + t.getId());
				System.out.println("Hello");
				Thread.sleep(1500);
			} catch(Exception e) {
				System.out.println("Error: " + e);
			}
			//System.out.println(i);
		}
	}
}

class Th2 extends Thread {
	public void run() {
		//Thread.currentThread();
		//Thread t = Thread.currentThread();
		//t.setPriority(5);
		//System.out.println("Thread status: " + t.isAlive());
		for(int i = 6; i <= 10; i++) {
			try {
				//System.out.println("ID = " + t.getId());
				System.out.println("Cheerio");
				Thread.sleep(1500);
			} catch(Exception e) {
				System.out.println("Error: " + e);
			}
			//System.out.println(i);
		}
	}
}
