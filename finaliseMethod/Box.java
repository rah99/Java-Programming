package finaliseMethod;

public class Box {
	Box() {
		super();
		System.out.println("This object reference is located on the heap at: " + this);
	}
	
	protected void finalized() {
		System.out.println("!!Finalized!! " + this);
	}
}
