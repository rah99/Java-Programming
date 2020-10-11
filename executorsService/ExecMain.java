package executorsService;

//import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;

public class ExecMain {

	private static int ID = 0;
	private static int IntVal = 40;
//	private static int IntVal = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
//		int UserInput = 4;
//		int UserPool = 1;
		int coreCount = Runtime.getRuntime().availableProcessors(); // Checks the number of processors available on the machine the program is running on
//		int Delay = 10;
//		int iniDelay = 15;
//		int IntVal = 10;
//		int ID = 0;
		// Getter and Setters
//		GetterAndSetter setter = new GetterAndSetter();
//		final int Intval = 10;
//		
		setIntVal(IntVal);
		// **Section One""
		// All tasks are run simultaneously - multiple threads
			
//		// Create thread pool - FixedThreadPool = asynchronous
////		ExecutorService service = Executors.newFixedThreadPool(UserPool); // IO intensive
		ExecutorService service = Executors.newFixedThreadPool(coreCount); // CPU intensive
		
		for (int j = 0; j < coreCount; j++) { // Source = https://stackoverflow.com/questions/15862271/java-compute-intensive-task
//			setter.setID(j);
			final int ID = j;
			setID(ID);
			service.execute(new ServerCPUIntensiveTask());
//			System.out.println("This computer has: " + coreCount + " active cores.");
		}
		
//		for (int j = 0; j < coreCount; j++) {
//			final int ID = j;
//			service.submit(new Runnable() {
//				public void run() {
//					for (int i = 0; i < Integer.MAX_VALUE; i++) {
//						System.out.println(ID+" worker: "+i + ": " + fib(new BigInteger(String.valueOf(i))));
//					}
//				}
//			});
//		}
		
		// Create thread pool - CachedThreadPool = synchronised
//		ExecutorService service = Executors.newCachedThreadPool(); // Do not specify number of threads as this will create a new thread if others are busy
		
		// Create thread pool - schedule, scheduleAtFixedRate, scheduleAtFixedDelay = used where something may need to be check e.g. check credentials (Delay Queue)
		// 1. Scheduling of tasks
//		ScheduledExecutorService service = Executors.newScheduledThreadPool(UserInput);
		
		// Run task after 10 seconds
//		service.schedule(new Server(), Delay, TimeUnit.SECONDS);
		
		// Run repeatedly every 10 seconds
//		service.scheduleAtFixedRate(new Server(), iniDelay, Delay, TimeUnit.SECONDS);
		
		// Run repeatedly 10 seconds after previous task completes
//		service.scheduleWithFixedDelay(new Server(), iniDelay, Delay, TimeUnit.SECONDS);
		
		// Tasks to be executed
//		for (int i = 0; i < UserInput; i++) {
//			service.execute(new ServerCPUIntensiveTask());
//		}
//		
//		System.out.println(Thread.currentThread().getName());
		
		// **Section Two**
		// All tasks run one after the other simultaneously - single thread
		// To make this happen reduce the thread pool to 1
//		ExecutorService service = Executors.newFixedThreadPool(UserPool); // IO intensive
		
		// Tasks to be executed
//		for (int i = 0; i < UserInput; i++) {
//			service.execute(new ServerCPUIntensiveTask());
//		}
//		
//		System.out.println(Thread.currentThread().getName());
		
		service.shutdown(); // This otherwise program hangs
//		try {
//			if (!service.awaitTermination(10, TimeUnit.SECONDS)) {
//				System.err.println("Thread did not finish!");
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
		
//		public static BigInteger fib(BigInteger n) {
//			if (n.compareTo(BigInteger.ONE) == -1 || n.compareTo(BigInteger.ONE) == 0 ) return n;
//			else 
//				return fib(n.subtract(BigInteger.ONE)).add(fib(n.subtract(BigInteger.ONE).subtract(BigInteger.ONE)));
//		}

	public static int getIntVal() {
		return IntVal;
	}

	public synchronized static void setIntVal(int intVal) {
		IntVal = intVal;
	}

	public static int getID() {
		return ID;
	}

	public synchronized static void setID(int iD) {
		ID = iD;
	}
}
