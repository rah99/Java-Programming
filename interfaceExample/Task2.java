package interfaceExample;

//class Task2 extends CA implements Runnable { // This also works for instances where something from the same class may need to be accessed eg. where 2 nuts are needed for a bolt
class Task2 implements Runnable {
	@Override
	public void run() {
		for(int doc = 1; doc <= 10; doc++) {
			System.out.println("** Printing document # " + doc + " - Printer 3");
		}
	}
}