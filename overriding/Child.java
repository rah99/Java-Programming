package overriding;

public class Child extends Parent {

	@Override
	public void talk() {
		super.talk();
		System.out.println("Hello from Child");
	}
}
