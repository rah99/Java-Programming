package supermarketQueues;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;
import java.util.Queue;
import java.util.Iterator;

/** Klasa reprezentuje miejsce przed i za kasami.Zajmuje siê lsoowaniem nowych klientów.*/
public class SpawningPlace implements Serializable{
	static {
		doneClients = new LinkedList();
	}
	
	public SpawningPlace() {
	    waitingClients = new LinkedList();
		minTime = 1;
		maxTime = 1;
		drawTime();
	}
	/**Odejmuje czas od klientów za kasami, a tak¿e odlicza jedn¹ sekundê od czasu pojawienia siê klienta. */
	public void makeTick() {
		if(timeToShow == 0) {
			waitingClients.add( new Client() );
			drawTime();
		} else 
			timeToShow--;
		
		Iterator<Client> iter = doneClients.iterator();
		while(iter.hasNext()) {
			if( iter.next().hasToLeave() ) 
				iter.remove();
		}
	}
	/** Zwraca jednego klienta czekaj¹cego przed kasami.*/
	public Client getClient() {
		if( waitingClients.isEmpty() == false)
			return waitingClients.poll();
		return null;
	}
	/** Sprawdza czy przed kasami znajduje siê jakiœ klient.*/
	public boolean isClient() {
		if( waitingClients.isEmpty() == false)
			return true;
		return false;
	}
	/** Dodaje jednego klienta do miejsa przed kasami.*/
	public void addClient(int amount) {
		for(int i = 0; i < amount; ++i) {
			waitingClients.add( new Client() );
		}
	}
	
	/** Przemieszcza klienta do miejsca za kasami.*/
	public static void dumpClient( Client client) {
		doneClients.add( client );
	}
	
	/** Zmienia rozk³¹d, z którego losowany jest czas nadejœcia klienta.*/
	public void changeDistribution(int min, int max) {
		minTime = min;
		maxTime = max;
	}
	
	/** Wypisuje na konsole stan miejsc za i przed kasami.*/
	public void writeOut() {
		System.out.println( "CZEKA " + waitingClients.size() + " klientów.");
		System.out.println( "OPUSZCZA " + doneClients.size() + " klientów.");
		
	}
	/** losuje czas nadejœcia klienta. */
	private void drawTime() {
		timeToShow = (new Random().nextInt() % minTime) + (maxTime - minTime);
	}
	/** Zwraca listê kleintów przed kasami.*/
	public LinkedList<Client> getWaitingClients() {
		return waitingClients;
	}
	/** Zwraca listê klientów za kasami.*/
	public LinkedList<Client> getDoneClients() {
		return doneClients;
	}
	
	private int timeToShow;
	private int minTime;
	private int maxTime;
	private LinkedList<Client> waitingClients;
	private static LinkedList<Client> doneClients;

}
