package tMethods;

public class TMethods {
	public static void main(String[] args) {
		Th1 t11 = new Th1();
		//		t11.setPriority(10);
		Th1 t12 = new Th1();
		//		t12.setPriority(8);
		Th1 t13 = new Th1();
		//		t13.setPriority(6);

		Th2 t21 = new Th2();
		//		t21.setPriority(9);
		Th2 t22 = new Th2();
		//		t22.setPriority(7);
		Th2 t23 = new Th2();
		//		t23.setPriority(5);

		// ***Run Threads in PARALLEL***
		// ***With WAIT***
		System.out.println("t11 ID = " + t11.getId());
		t11.start();
		System.out.println("t21 ID = " + t21.getId());
		t21.start();
		while(t11.isAlive() || t12.isAlive() || t13.isAlive() || t22.isAlive() || t23.isAlive()) {
			try {
				t21.wait(10);
			} catch(Exception e) {

			}
		}
		////			try { // Setting a sleep state to enable firing of threads in sequential order - however if the iterations are extensive this could cause further problems, therefore this needs to be handled in a different way such as priority
		////				Thread.sleep(10);
		////			} catch(Exception e) {
		////				System.out.println("Error: " + e);
		////			}
		System.out.println("t13 ID = " + t13.getId());
		t12.start();
		while(t11.isAlive() || t13.isAlive() || t21.isAlive() || t22.isAlive() || t23.isAlive()) {
			try {
				t12.wait(10);
			} catch(Exception e) {

			}
		}
		////			try {
		////				Thread.sleep(20);
		////			} catch(Exception e) {
		////				System.out.println("Error: " + e);
		////			}
		System.out.println("t22 ID = " + t22.getId());
		t22.start();
		while(t11.isAlive() || t12.isAlive() || t13.isAlive() || t21.isAlive() || t23.isAlive()) {
			try {
				t22.wait(10);
			} catch(Exception e) {

			}
		}
		////			try {
		////				Thread.sleep(30);
		////			} catch(Exception e) {
		////				System.out.println("Error: " + e);
		////			}
		System.out.println("t13 ID = " + t13.getId());
		t13.start();
		while(t11.isAlive() || t12.isAlive() || t21.isAlive() || t22.isAlive() || t23.isAlive()) {
			try {
				t13.wait(10);
			} catch(Exception e) {

			}
		}
		////			try {
		////				Thread.sleep(40);
		////			} catch(Exception e) {
		////				System.out.println("Error: " + e);
		////			}
		System.out.println("t23 ID = " + t23.getId());
		t23.start();
		while(t11.isAlive() || t12.isAlive() || t13.isAlive() || t21.isAlive() || t22.isAlive()) {
			try {
				t23.wait(10);
			} catch(Exception e) {

			}
		}
		////			try {
		////				Thread.sleep(50);
		////			} catch(Exception e) {
		////				System.out.println("Error: " + e);
		////			}
		//		if(t11.isAlive() || t12.isAlive() || t13.isAlive() || t22.isAlive() || t23.isAlive()) {
		//			try {
		//				t21.wait(10);
		//			} catch(Exception e) {
		//					
		//			}
		//		} else if(t11.isAlive() || t13.isAlive() || t21.isAlive() || t22.isAlive() || t23.isAlive()) {
		//			try {
		//				t12.wait(10);
		//			} catch(Exception e) {
		//				
		//			}
		//		} else if(t11.isAlive() || t12.isAlive() || t13.isAlive() || t21.isAlive() || t23.isAlive()) {
		//			try {
		//				t22.wait(10);
		//			} catch(Exception e) {
		//				
		//			}
		//		} else if(t11.isAlive() || t12.isAlive() || t21.isAlive() || t22.isAlive() || t23.isAlive()) {
		//			try {
		//				t13.wait(10);
		//			} catch(Exception e) {
		//				
		//			}
		//		} else if(t11.isAlive() || t12.isAlive() || t13.isAlive() || t21.isAlive() || t22.isAlive()) {
		//			try {
		//				t23.wait(10);
		//			} catch(Exception e) {
		//				
		//			}
		//		}

		////		System.out.println("ID=" + t1.getId());
		//***Without WAIT***
		//		t11.start();
		//		t21.start();
		//		t12.start();
		//		t22.start();
		//		t13.start();
		//		t23.start();

		// ***Run Threads one after the other using JOIN***
		//		System.out.println("Name of this Thread is " + t1.getName());
		//		t1.setName("Rob");
		//		System.out.println("Name of this Thread after changing it's name is " + t1.getName());
		//		
		//		System.out.println("Priority of Thread is " + t1.getPriority()); // Default is 5 of range 1 (lowest) to 10 (highest)
		//		t1.setPriority(10);
		//		System.out.println("Priority of Thread is now " + t1.getPriority());
		//		System.out.println("ID=" + t11.getId());

		//		t11.setName("One");
		//		System.out.println("Thread " + t11.getName() + " started");
		//		t11.start();
		//			try {
		//				t11.join();
		//			} catch(Exception e) {
		//				System.out.println("Error: " + e);
		//			}
		//		System.out.println("Thread " + t11.getId() + " status " + t11.isAlive());
		//		//System.out.println("ID=" + t12.getId());
		//		t12.setName("Two");
		//		System.out.println("Thread " + t12.getName() + " started");
		//		t12.start();
		//			try {
		//				t12.join();
		//			} catch(Exception e) {
		//				System.out.println("Error: " + e);
		//			}
		//		System.out.println("Thread " + t12.getId() + " status " + t12.isAlive());
		//		//System.out.println("ID=" + t13.getId());
		//		t13.setName("Three");
		//		System.out.println("Thread " + t13.getName() + " started");
		//		t13.start();
		//System.out.println("Thread " + t13.getName() + " status " + t13.isAlive());
	}
}
