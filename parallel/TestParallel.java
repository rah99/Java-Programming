package parallel;

public class TestParallel {

	public static void main(String[] args) {
		Table1 obj1 = new Table1(); // 2 objects = in parallel
		Table2 obj2 = new Table2();

		Th1 t11 = new Th1(obj1);
		Th2 t21 = new Th2(obj2);

		t11.start();
		t21.start();
	}
}
