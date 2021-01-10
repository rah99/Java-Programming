package supermarketQueues;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/** Klasa czytajaca znaki z klawiatury w trybie ręcznym przy wyłączonym Gui.*/
class Reader implements Runnable {

	public Reader(SimulationModel sim, Timer tim) {
		simulation = sim;
		timer = tim;
	}
	
	@Override
	public void run() {
		String s;
		Scanner in = new Scanner(System.in);
		for(;;) {
			s = in.next();
			
			/*Zmienia tryb na przeciwny i w zależności od decyzji zwalnia model symulacji na jedną sekundę. */
			if( s.equals("r") && !simulation.isManualMode() ) {
				timer.changeWaiting();
				simulation.changeMode();
			} else if( s.equals("r") && simulation.isManualMode() ) {
				timer.changeWaiting();
				try {
					timer.check();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				simulation.changeMode();
			} else if( s.equals("t") && simulation.isManualMode() ) {
				timer.makeTick();
			}
			
		}
	}
	
	private SimulationModel simulation;
	private Timer timer;
}
