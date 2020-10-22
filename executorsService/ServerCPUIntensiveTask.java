package executorsService;

import java.math.BigInteger;

//import com.sun.jdi.IntegerValue;

public class ServerCPUIntensiveTask implements Runnable {
	
	private int intVal = 0;
	private int ID = 0;
	
//	public void Vars(GetterAndSetter setter) {
//	}
	
	public void run() {
		for (int i = 0; i < ExecMain.getIntVal(); i++) { // Can use Integer.MAX_VALUE
			System.out.println(Thread.currentThread().getName() + " " + ExecMain.getID() + " worker " + ": " + fib(new BigInteger(String.valueOf(i))) + " Iterations: " + i);
		}
//		System.out.println((10 * 500)/3.44);
//		System.out.println(Thread.currentThread().getName());
	}
	
	public static BigInteger fib(BigInteger n) {
	    if (n.compareTo(BigInteger.ONE) == -1 || n.compareTo(BigInteger.ONE) == 0 ) return n;
	    else 
	        return fib(n.subtract(BigInteger.ONE)).add(fib(n.subtract(BigInteger.ONE).subtract(BigInteger.ONE)));
	}
	
	public int getIntVal() {
		return this.intVal; 
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setIntval(int intVal) {
		this.intVal = intVal;
	}
	
	public void setId(int ID) {
		this.ID = ID;
	}
}
