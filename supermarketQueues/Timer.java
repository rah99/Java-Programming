package supermarketQueues;

public class Timer implements Runnable {
	 
	public Timer(SimulationModel sim, int count) {
		counter = count;
		simulation = sim;
		waiting = false;
	}
	 
	
	/** Co sekund� zwi�ksza licnzik o 1 i pobudza symulacj�.*/
	public void run() {
		try {
			while (true) {
				simulation.changeReady();
				Thread.sleep(1000);
				check();
				counter++;
			}
		} catch (InterruptedException ex) {
			System.exit(0);
		}
	}
	 
	/** Metoda sprawdza czy zegar musi si� zatrzyma� lyb czy zosta� wznowiony.*/
	public synchronized void check() throws InterruptedException {
		if (waiting == true)
			wait();
		if (waiting == false)
			notifyAll();
	}
	 
	/** Metoda sygnalizuj�ca zegarowi, aby ten przy nastepnej p�tli si� zatrzyma�. */
	public synchronized void changeWaiting() {
		waiting = !waiting;
	}
	 
	/** Zwraca czas. */
	public int getTime() {
		return counter;
	}
	/** Zegar jednorazowo pobudza modu� symulacji.*/
	public void makeTick() {
		simulation.changeReady();
		counter++;
	}
	
	 private int counter;
	 private boolean waiting;
	 private SimulationModel simulation;
	 Exception ex = new Exception();
}
