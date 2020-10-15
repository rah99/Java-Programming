package classwork_lessonDeadLock;

public class DeadLockingMain {

	public static void main(String[] args) {
		Deadlock deadlock = new Deadlock();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					deadlock.method1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					deadlock.method2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t2.start();
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					deadlock.method3();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t3.start();
	}
}
