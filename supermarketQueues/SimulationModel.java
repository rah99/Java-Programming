package supermarketQueues;

import java.io.IOException;
import java.util.Iterator;

/** Główna klasa reprezentująca symulację.*/
public class SimulationModel {
	public static void main(String[] args) {
		
		SimulationModel simulation = new SimulationModel();
		
		/*Tworzy zegar, który odmierza czas i co sekundę zatrzymuję symulację. */
	    simulation.timer = new Timer(simulation, 0);
		Thread t = new Thread(simulation.timer);
		t.start();
		
	    simulation.doSimulationLoop();
	}
	
	/** Główna petla programu*/
	public SimulationModel() {
		
		decision = new int[10];
		ready = false;
		isManualMode = true;
		isSchedulingOn=true;
		isGuiOn=true;
		spawningPlace = new SpawningPlace();
		boxOfficeNumber = 0;
		boxOffices = new BoxOffice[10];
		
		for(int i = 0; i < 10; i++) {
			boxOffices[i] = new BoxOffice();
		}
		
		/*Tworzy klienta sieciowego pobierajacego i wysyłającego dane. */
		try {
			lanClient = new LanClient(this, timer);
			Thread t = new Thread(lanClient);
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Couldn't connect to the server.");
			isGuiOn = false;
			isSchedulingOn = false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** Zmienia tryb. */
	public void changeMode() {
		isManualMode = !isManualMode;
	}
	/** Sprawdza jaki jest tryb.*/
	public boolean isManualMode() {
		return isManualMode;
	}
	/** Metoda zawieszająca symulację używana przez zegar.*/
	public synchronized void changeReady() {
		ready = !ready;
		notifyAll();
	}
	/** Urochamia wą░tek czytający z klawiatury.*/
	private void startReader() {
		Reader reader = new Reader(this, timer);
		Thread t2 = new Thread(reader);
		t2.start();
	}
	/** Główna pętla programu */
	public synchronized void doSimulationLoop() {
		
		for(;;) {
			/*Czeka na sygnał zegara. */
			System.out.println("PETLA DOSIMULATION LOOP");
			try {
				if(ready == false)
					wait();
				ready = !ready;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* Sprawdza czy kleintowi sieciowemu udasło się nawiązać połączenie. */
			if( lanClient != null && lanClient.isConnected() == false ) {
				isGuiOn = false;
				isSchedulingOn = false;
			}
			/*if( lanClient != null && lanClient.isConnected() == true ) {
			    isGuiOn = true;
			    if(lanClient.isScheduling())
				isSchedulingOn = true;
			}*/
			
			spawningPlace.makeTick();
			/*Jeżeli nie ma komunikacji z Gui uruchom wątek wczytujący znaki z klawiatury. */
			if(isGuiOn == false) {
				if(readerOn == false) {
					startReader();
					readerOn = true;
				}
			} else if(lanClient != null && lanClient.isNew()) {
				/*Jeżeli klient sieciowy zgłasza nadejście nowych danych od Gui zapisz wszytskie. */
				spawningPlace.addClient( lanClient.getNewClients() );
				spawningPlace.changeDistribution(lanClient.getMinClientTime(), lanClient.getMaxClientTime());
				Client.changeDistribution(lanClient.getMinTimeOfService(), lanClient.getMaxTimeOfService());
				BoxOffice.changeDistribution( lanClient.getMinClosedTime(), lanClient.getMaxOpenTime(), 
											  lanClient.getMinClosedTime(), lanClient.getMaxClosedTime());
				lanClient.setNew(false);
				if( !lanClient.isGui() )
					isGuiOn = false;
				else if( lanClient.isGui() )
					isGuiOn = true;
			}
			if(lanClient!=null)
			if( !lanClient.isScheduling() ) 
				isSchedulingOn = false;
				else isSchedulingOn=true;
			int [] stany=new int [10];
			int [] kolejki=new int [10];
			for (int w=0; w<10; ++w){
				kolejki[w]=boxOffices[w].size();
				stany[w]=boxOffices[w].getState().toInt();
			}
			if(isSchedulingOn == true) {
				System.out.println("Scheduling is on");
				try {
					
					/*Jeżeli istnieje połączenie z modułem szeregującym, wyślij do niego informacje o kasach. */
					lanClient.sendMessage( new msg("1", "2", boxOffices, spawningPlace.getWaitingClients().size(), kolejki,stany) );
					System.out.println("WYSLANE KASY:");
					for (int k=0; k<10 ; ++k )
						System.out.println(boxOffices[k].size());
					while( !lanClient.hasSchedResponded() ) { }
					/*program czeka, aż nadejdzie jakaś wiadomość od modułu szeregującego. */
						lanClient.setSchedResponded(false);
						if( !lanClient.isScheduling() ) 
							isSchedulingOn = false;
						if (lanClient.isScheduling()) {
								decision = lanClient.getDecision();
							for(int i = 0; i < 10; ++i) {
								/*Wypełnij kolejki zgodnie z uszeregowaniem. */
								for(int j = 0*(boxOffices[i].size()); j < decision[i]; ++j) {
									//if( spawningPlace.isClient() )
										boxOffices[i].addClient( spawningPlace.getClient() );
								}
							}
						}
				} catch (IOException e) {
					System.out.println("Error: Couldn't send message");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
			/*Jeżeli nie ma kontaktu z modułem szeregującym przydziel nowych klienów samemu. */
			if(isSchedulingOn == false) {
			
				System.out.println("isScheduling false");
				System.out.println("Sam Przydzielam");
				for(;;) {
					int i = 0;
					if( spawningPlace.isClient() ){
						boxOfficeNumber=0;
						for(i = 0; i < 11; ++i) {
							if( boxOffices[ boxOfficeNumber ].isAvailble() == true ) {
								boxOffices[ boxOfficeNumber ].addClient( spawningPlace.getClient() );
								//boxOfficeNumber = (++boxOfficeNumber) % 10;
								break;
							}else{
							boxOfficeNumber = (++boxOfficeNumber) % 10;
							}
							}}
					else break;
					
					if(i == 11)
						break;
				}
			}
			
			/*Jeżeli Gui jest aktywne wyślij mu informacje o stanie symulacji. */
			int[][] idklientow=new int[10][20];
			int[] czasyklientow=new int[10];
			Client klientroboczy;
			for (int w=0; w<10; ++w){
				kolejki[w]=boxOffices[w].size();
				stany[w]=boxOffices[w].getState().toInt();
			}
			for (int i=0;i<10;++i){
				int j=0;
				czasyklientow[i]=boxOffices[i].getTimeOfService();
				Iterator<Client> iter = boxOffices[i].getIterator();
				while(iter.hasNext())
				{	klientroboczy=iter.next();
					idklientow[i][j]=klientroboczy.getId();
				    j++;
				}}
			int[] idklientowprzedkasami= new int[100];
			Iterator<Client> iter2 = spawningPlace.getWaitingClients().iterator();
			int licznik=0;
			while(iter2.hasNext())
			{	klientroboczy=iter2.next();
				idklientowprzedkasami[licznik]=klientroboczy.getId();
				++licznik;
			}
			Iterator<Client> iter3 = spawningPlace.getDoneClients().iterator();
			int licznik2=0;
			int[] idklientowzakasami= new int[100];
			while(iter3.hasNext())
			{	klientroboczy=iter3.next();
				idklientowzakasami[licznik2]=klientroboczy.getId();
				++licznik2;
			}
		
			if(isGuiOn) {
				try {
					System.out.println("SPAWNING PLACE CL:");
					System.out.println(spawningPlace.getWaitingClients().size());
					System.out.println("SPAWNING PLACE DONE CL:");
					System.out.println(spawningPlace.getDoneClients().size());
					lanClient.sendMessage( new msg("1", "3",  boxOffices,kolejki,stany,idklientow, czasyklientow, spawningPlace.getWaitingClients(),idklientowprzedkasami, spawningPlace.getDoneClients(), idklientowzakasami, timer.getTime() ) );
				} catch (IOException e) {
					System.out.println("Error: Couldn't send message to the gui.");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			/*Oblicz stany kas po sekundzie, a następnie je wypisz na konsole */
			for(BoxOffice office : boxOffices) 
				office.makeTick();
				
			if(isGuiOn == false || isGuiOn == true) {
				spawningPlace.writeOut();
				for(BoxOffice office : boxOffices) 
					office.writeOut();
			
				System.out.printf("---------- " + boxOfficeNumber + "   " + end + " -------------------\n");
				if(end++ == 100)
					System.exit(0);
			}
	  }
	}
	
	private int boxOfficeNumber;
	private SpawningPlace spawningPlace;
	private BoxOffice[] boxOffices;
	private int[] decision;
	private boolean ready;
	private boolean readerOn;
	private Timer timer;
	private boolean isManualMode;
	private LanClient lanClient;
	private int end = 0;
	private boolean isGuiOn;
	private boolean isSchedulingOn;
}
