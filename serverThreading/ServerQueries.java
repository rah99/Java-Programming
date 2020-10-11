package serverThreading;

class ServerQueries {

	public static void parallelQuery() {		
		Server obj1 = new Server();
		Server obj2 = new Server();
		Server obj3 = new Server();
		Server obj4 = new Server();

		Thread1Parallel t1 = new Thread1Parallel(obj1);
		Thread2Parallel t2 = new Thread2Parallel(obj2);
		Thread3Parallel t3 = new Thread3Parallel(obj3);
		Thread4Parallel t4 = new Thread4Parallel(obj4);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void syncQuery() {
		Server obj1 = new Server();
		Server obj2 = new Server();
		Server obj3 = new Server();
		Server obj4 = new Server();

		Thread1 t1 = new Thread1(obj1);
		Thread2 t2 = new Thread2(obj2);
		Thread3 t3 = new Thread3(obj3);
		Thread4 t4 = new Thread4(obj4);

		// Look here for possible solution https://stackoverflow.com/questions/9939076/wait-until-child-threads-completed-java

		//		t1.start();
		//		t2.start();
		//		t3.start();
		//		t4.start();
		//		while (!t1.equals(obj1) || !t2.equals(obj2) || !t3.equals(obj3) && t4.equals(obj4)) {
		//			try {
		//				Thread.sleep(100);
		//				t4.join();
		//			} catch (InterruptedException e) {
		//				e.printStackTrace();
		//			}
		//		}
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t3.start();
		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t4.start();
		try {
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
