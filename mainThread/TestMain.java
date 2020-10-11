package mainThread;

public class TestMain {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(MainThread.isMainThread());
				System.out.println(Thread.currentThread().getId());
			}
		}).start();
	}

}
/* **Resources** 
 * https://stackoverflow.com/questions/9063153/is-there-any-way-to-distinguish-the-main-thread-from-any-threads-that-it-spawns
*/
