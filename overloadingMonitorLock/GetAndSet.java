package overloadingMonitorLock;

public class GetAndSet {

	private int UserInput;
	
	public GetAndSet() {
		
	}
	
	public GetAndSet(int newUserInput) {
		UserInput = newUserInput;
	}

	public int getUserInput() {
		return UserInput;
	}

	public void setUserInput(int i) {
		this.UserInput = i;
	}
}
