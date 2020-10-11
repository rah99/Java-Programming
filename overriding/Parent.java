package overriding;

public class Parent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Child c = new Child();
		c.talk();
	}

	public void talk() {
		System.out.println("Hello");
	}

}
