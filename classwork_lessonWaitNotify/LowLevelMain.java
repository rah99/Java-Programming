package classwork_lessonWaitNotify;

public class LowLevelMain {

	public static void main(String[] args) {
		LowLevelProcessor processor = new LowLevelProcessor();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.producer();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.consumer();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t2.start();
		
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.consumer();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t3.start();
	}
}
