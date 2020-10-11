package maths;

public class BBMathsMain {

	public static void main(String[] args) {
		BrokenMaths maths = new BrokenMaths(42);
		maths.bv = 78;
		System.out.println(maths.add(2, 2));
		System.out.println(maths.minus(8, 2));
		System.out.println(maths.multi(3, 2));
	}

}
