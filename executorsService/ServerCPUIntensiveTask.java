package executorsService;

import java.math.BigInteger;

//import com.sun.jdi.IntegerValue;

public class ServerCPUIntensiveTask implements Runnable {
	
	private volatile int intVal = 0;
	private volatile int ID = 0;
	
	public ServerCPUIntensiveTask() {
		
	}
	
	public ServerCPUIntensiveTask(int intVal, int iD) {
		super();
		this.intVal = intVal;
		this.ID = iD;
	}
	
//	public int getIntVal() { // Getters and Setters are not needed as the value has already been set in the main class?
//		return this.intVal; 
//	}
//	
//	public int getID() {
//		return this.ID;
//	}
//	
//	public void setIntval(int intVal) {
//		this.intVal = intVal;
//	}
//	
//	public void setId(int ID) {
//		this.ID = ID;
//	}
	
	public void run() {
		for (int i = 0; i < intVal; i++) { // Can use Integer.MAX_VALUE
			System.out.println(Thread.currentThread().getName() + " " + ID + " worker " + ": " + fib(new BigInteger(String.valueOf(i))) + " Iterations: " + i);
		}
	}
	
	public static BigInteger fib(BigInteger n) {
	    if (n.compareTo(BigInteger.ONE) == -1 || n.compareTo(BigInteger.ONE) == 0 ) return n;
	    else 
	        return fib(n.subtract(BigInteger.ONE)).add(fib(n.subtract(BigInteger.ONE).subtract(BigInteger.ONE)));
	}
}
