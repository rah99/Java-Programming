package supermarketQueues;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Queue;


public class BoxOffice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2732160548992841849L;
	static {
		minTimeOpen = 10;
		maxTimeOpen = 30;
		
		minTimeClosed = 10;
		maxTimeClosed = 30;
	}
	
	public BoxOffice() {
		queue = new LinkedList<Client>();
		state = BoxOfficeState.OPEN;
		drawTime();
	}

	public boolean isAvailble() {
		if(state == BoxOfficeState.OPEN && queue.size() < 20)
			return true;
		return false;
	}
	
	public BoxOfficeState getState() {
		return state;
	}
	
	public void makeTick() {
		if(state == BoxOfficeState.OPEN) {
			if( timeToChangeState == 0)
				state = BoxOfficeState.CLOSING;
			else {
				timeToChangeState--;
				if( !queue.isEmpty() ) {
				queue.peek().makeTick();
					if( queue.peek().hasFinished() )
						SpawningPlace.dumpClient( queue.poll() );
				}
			}
		}
		
		if(state == BoxOfficeState.CLOSING) {
			if(queue.isEmpty() == true) {
				state = BoxOfficeState.CLOSED;
				drawTime();
			} else {
				if( !queue.isEmpty() ) {
					queue.peek().makeTick();
						if( queue.peek().hasFinished() )
							queue.poll();
					}
			}
		}
		
		if(state == BoxOfficeState.CLOSED) {
			if( timeToChangeState == 0) {
				state = BoxOfficeState.OPEN;
				drawTime();
			} else
				timeToChangeState--;
		}
	}
	
	public void writeOut() {
		System.out.println( queue.size() + "      " + timeToChangeState + " " + state );
	}
	
	public void addClient(Client c) {
		queue.add(c);
	}
	
	public static void changeDistribution(int minOpen, int maxOpen, int minClosed, int maxClosed) {
		minTimeOpen = minOpen;
		maxTimeOpen = maxOpen;
		minTimeClosed = minClosed;
		maxTimeClosed = maxClosed;	
	}
	
	private void drawTime() {
		if(state == BoxOfficeState.OPEN)
		 timeToChangeState = (new Random().nextInt() % minTimeOpen + (maxTimeOpen - minTimeOpen));
		else
		 timeToChangeState = (new Random().nextInt() % minTimeClosed + (maxTimeOpen - minTimeClosed));
	}
	
	public int size() {
		return queue.size();
	}
	
	public int getTimeOfService() {
		if( queue.peek() != null )
			return queue.peek().timeOfService();
		else return 0;
	}
	
	public Iterator<Client> getIterator() {
		return queue.iterator();
	}
	
	private int timeToChangeState;
	private static int minTimeOpen;
	private static int maxTimeOpen;
	private static int minTimeClosed;
	private static int maxTimeClosed;
	private BoxOfficeState state;
	private Queue<Client> queue;
}

enum BoxOfficeState {
	OPEN, CLOSING, CLOSED;
	
	public String toString() {
		if( this.equals(BoxOfficeState.OPEN) )
			return "OPEN";
		else if( this.equals(BoxOfficeState.CLOSING) )
			return "CLOSING";
		else return "CLOSED";
	}
	public int toInt() {
		if( this.equals(BoxOfficeState.OPEN) )
			return 1;
		else if( this.equals(BoxOfficeState.CLOSING) )
			return 2;
		else return 3;
	}
}