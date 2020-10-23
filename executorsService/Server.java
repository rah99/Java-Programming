package executorsService;

public class Server implements Runnable {
	
//	private int intVal = 0;
//	private int ID = 0;

	@Override
	synchronized public void run() {
		System.out.println(Thread.currentThread().getName());
	}
	
//	public int getIntVal() {
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
}
