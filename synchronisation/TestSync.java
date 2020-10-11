package synchronisation;

public class TestSync {

	public static void main(String[] args) {
		Table obj = new Table(); // 1 object = synchronised one after the other

		Th1 t1 = new Th1(obj);
		Th2 t2 = new Th2(obj);

		t1.start();
		t2.start();
	}
}
