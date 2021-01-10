package supermarketQueues;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;
import java.util.Queue;
import java.util.Iterator;

/** Klasa reprezentuje miejsce przed i za kasami.Zajmuje si� lsoowaniem nowych klient�w.*/
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
	/**Odejmuje czas od klient�w za kasami, a tak�e odlicza jedn� sekund� od czasu pojawienia si� klienta. */
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
	/** Zwraca jednego klienta czekaj�cego przed kasami.*/
	public Client getClient() {
		if( waitingClients.isEmpty() == false)
			return waitingClients.poll();
		return null;
	}
	/** Sprawdza czy przed kasami znajduje si� jaki� klient.*/
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
	
	/** Zmienia rozk��d, z kt�rego losowany jest czas nadej�cia klienta.*/
	public void changeDistribution(int min, int max) {
		minTime = min;
		maxTime = max;
	}
	
	/** Wypisuje na konsole stan miejsc za i przed kasami.*/
	public void writeOut() {
		System.out.println( "CZEKA " + waitingClients.size() + " klient�w.");
		System.out.println( "OPUSZCZA " + doneClients.size() + " klient�w.");
		
	}
	/** losuje czas nadej�cia klienta. */
	private void drawTime() {
		timeToShow = (new Random().nextInt() % minTime) + (maxTime - minTime);
	}
	/** Zwraca list� kleint�w przed kasami.*/
	public LinkedList<Client> getWaitingClients() {
		return waitingClients;
	}
	/** Zwraca list� klient�w za kasami.*/
	public LinkedList<Client> getDoneClients() {
		return doneClients;
	}
	
	private int timeToShow;
	private int minTime;
	private int maxTime;
	private LinkedList<Client> waitingClients;
	private static LinkedList<Client> doneClients;

}
