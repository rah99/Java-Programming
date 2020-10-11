package overloading;

public class Child extends Parent {

	@Override
	public void walk() {
		super.walk(); // This refers back to the main method "walk"
		System.out.println("Walked today");
	}

	public void walk(Double miles, int times) {
		System.out.println("Walked " + miles + " miles " + times + " times today");
	}

	public void walk(Double miles, int times, String place) {
		System.out.println("Walked " + miles + " miles " + times + " times around " + place + " today");
	}
}
