package supermarketQueues;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/** Klasa klienta.*/
public class Client implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3245530742031350464L;
	static {
		minTime = 6;
		maxTime = 20;
		globalId = 1;
	}
	/** Konstruktor dodaj�cy klientowi unikalny identyfikator. */
	public Client() {
		timeToLeave = 5;
		id = globalId++;
		drawTime();
	}
	/** Odejmuje jedn� sekund� czasu obs�ugi klienta*/
	public void makeTick() {
		timeOfService--;
	}

	public int getId() {
		return id;
	}
	/** Sprawdza czy czas ibs�ugi klienta si� zako�czy�.*/
	public boolean hasToLeave() {
		timeToLeave--;
		if( timeToLeave == 0)
			return true;
		else return false;
	}
	
	public int timeOfService() {
		return timeOfService;
	}
	
	public boolean hasFinished() {
		if( timeOfService == 0)
			return true;
		return false;
	}
	/** Zmienia rozk��d, z kt�rego losowany jest czas obs�ugi.*/
	public static void changeDistribution(int min, int max) {
		minTime = min;
		maxTime = max;
	}
	/** Losuje czas obs�ugi.*/
	private void drawTime() {
		timeOfService = (new Random().nextInt() % minTime + (maxTime - minTime));
	}
	
	private int timeOfService;
	private int timeToLeave;
	private static int minTime;
	private static int maxTime;
	private static int globalId;
	private int id;
}
