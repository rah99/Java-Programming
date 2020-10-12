package threadArrays;

public class ThreadsMain {
	
	private static int arrNum = 10;

	public static void main(String[] args) throws InterruptedException {
		Runnable hwodyObj = new ThreadRunnable();

		Thread []thread = new Thread[arrNum];
		for (int i = 0; i < arrNum; i++) {
			thread[i] = new Thread(hwodyObj, i + " ");
			thread[i].start();
		}
		for (int i = 0; i < arrNum; i++) {
			thread[i].join();
		}
	}
}
// https://www.youtube.com/watch?v=nuMHF6M_FdA