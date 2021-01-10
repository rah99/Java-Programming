package supermarketQueues;

import java.io.*;
import java.net.*;
import java.util.*;

public class LanClient implements Runnable {

	private String host;
	private String port;
	private Socket s = null;
	private boolean send;
	private ObjectOutputStream objectoutputstream;  
	private ObjectInputStream objectinputstream;
	private BufferedInputStream bis;

	LanClient(SimulationModel sim, Timer t) throws IOException, ClassNotFoundException, InterruptedException {

		simulation = sim;
		timer = t;
		configuration();
		int portint;
		portint = Integer.parseInt(port);
		send = true;
		s = new Socket(host, portint);
		s.setSoTimeout(2000);
		OutputStream outputstream = s.getOutputStream();  
		objectoutputstream = new ObjectOutputStream(outputstream);  
		InputStream inputstream = s.getInputStream();  
		bis = new BufferedInputStream(inputstream);
		objectinputstream = new ObjectInputStream(bis);
	}

	private void configuration(){
		StringBuilder text = new StringBuilder();
		File conf = new File("conf.ini") ;
		String readed;
		Scanner scanner = null;
		StringTokenizer token = null;
		try {
			scanner = new Scanner(new FileInputStream(conf));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {

			text.append(scanner.nextLine());
			readed = "";
			readed += text;

			token = new StringTokenizer(readed, ",");
			host = token.nextToken();
			if (token.hasMoreTokens())
				port = token.nextToken();
			System.out.println(host);
			System.out.println(port);
		}
		finally{
			scanner.close();
		}

	};
	/* Metoda wykorzystywana do wysy³ania wiadomoœci.*/
	public void sendMessage(msg message) throws IOException, ClassNotFoundException, InterruptedException {
		System.out.println("PROBUJE COS WYSLAC");
		objectoutputstream.writeObject(message);  
		System.out.println("Wysylanie:");
		System.out.println(message.getID());
		System.out.println(message.getTO());

	}
	/*Metoda pobiera obiekt msg. */
	private msg getMessage()  {
		msg message = null;
		try {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("ODBIERANIE: w FUKCJI GETMESSAGE");
			//if(bis.available()>0)
			message = (msg)objectinputstream.readObject();
			setConnected(true);
			if(message==null){
				System.out.println("NIC NIE ODEBRALEM");
			}
		} catch (IOException e) {
			System.out.println("WYJEBALO");
			setConnected(false);
			int portint = Integer.parseInt(port);
			/*try {
					if(!s.isConnected()){
					s.close();
					s = new Socket(host, portint);
					s.setSoTimeout(2000);
					OutputStream outputstream = s.getOutputStream();  
					objectoutputstream = new ObjectOutputStream(outputstream);  
					InputStream inputstream = s.getInputStream();  
					bis = new BufferedInputStream(inputstream);
					setConnected(true);}
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	

		if (message != null) {
			System.out.println(message.getID());
			System.out.println(message.getTO());
		}
		if(message==null)
			return null;
		else
			return message;
	}


	private void close() throws IOException, ClassNotFoundException, InterruptedException {
		objectinputstream.close();
		objectoutputstream.close();  
		//outputstream.close(); 
		//inputstream.close();  
		s.close();
	}

	/** Pêtla klienta sieciowego, której zadaniem jest pobieranie informacji i zapisaywanie danych.*/
	public void run() {
		while(true){
			System.out.println("SPRAWDZAM POCZTE");
			msg message = getMessage();
			if(message==null) System.out.println("Nic nie dostalem");
			if(message != null && message.getID().equals("3") && message.getTO().equals("1")) {
				System.out.println("DOSTALEM OD 3");
				if( message.tryb.toString()=="AUTOMATIC" && !simulation.isManualMode() ) {
					timer.changeWaiting();
					simulation.changeMode();
				} else if( message.tryb.toString()=="AUTOMATIC" && simulation.isManualMode() ) {
					timer.changeWaiting();
					try {
						timer.check();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					simulation.changeMode();
				} else if(message.tryb.toString()=="MANUAL" && simulation.isManualMode() ) {
					timer.makeTick();
				}
				setIsGui(message.isGuiOn);
				setIsScheduling(message.isSchedulingOn);
				setNew(true);
				//setManualMode(message.tryb);
				addNewClients(message.klienci);
				setClientTime(message.minczaspojawianiaklienta, message.maxczaspojawianiaklienta);
				setTimeOfService(message.minczasobslugiklienta, message.maxczasobslugiklienta);
				setOpenTime(message.minczasotwarciakasy, message.maxczasotwarciakasy);
				setClosedTime(message.minczaszamknieciakasy, message.maxczaszamknieciakasy);
			} else if(message != null && message.getID().equals("2") && message.getTO().equals("1")) {
				System.out.println("DOSTALEM OD 2");
				setDecision(message.decyzja); 
				setIsScheduling(message.isSchedulingOn);

				setIsGui(message.isGuiOn);
				if(isGui)
					System.out.println("JEST GUI");
				else
					System.out.println("NIE MA GUI");
				setIsScheduling(message.isSchedulingOn);
				if(isScheduling)
					System.out.println("JEST MODUL 2");
				else
					System.out.println("NIE MA MODUL 2");
				setSchedResponded(true);
			} else if (message !=null && message.getID().equals("0") && message.getTO().equals("1")){
				System.out.println("DOSTALEM OD 0");
				setIsGui(message.isGuiOn);
				if(isGui)
					System.out.println("JEST GUI");
				else
					System.out.println("NIE MA GUI");
				setIsScheduling(message.isSchedulingOn);
				if(isScheduling)
					System.out.println("JEST MODUL 2");
				else
					System.out.println("NIE MA MODUL 2");
				setSchedResponded(true);
			}
		}

	}

	/** Metody dostêpowe do kluczowych informacji. Metody te s¹ synchronizowane gdy¿ ze zmiennych równolegle mo¿e korzystaæ klient sieciowy jak i modu³ symulacji.*/
	public synchronized void setConnected(boolean b) {
		isConnected = b;
	}

	public synchronized boolean isConnected() {
		return isConnected;
	}
	/*
    public synchronized void setManualMode(boolean b) {
		isManualMode = b;
	}

    public synchronized boolean isManualMode() {
		return isManualMode;
	}*/

	public synchronized void addNewClients(int n) {
		newClients += n;
	}

	public synchronized int getNewClients() {
		int n = newClients;
		newClients = 0;
		return n;
	}

	public void setClientTime(int min, int max) {
		minClientTime = min;
		maxClientTime = max;
	}

	public synchronized int getMinClientTime() {
		return minClientTime;
	}

	public synchronized int getMaxClientTime() {
		return maxClientTime;
	}

	public synchronized void setTimeOfService(int min, int max) {
		minTimeOfService = min;
		maxTimeOfService = max;
	}

	public synchronized int getMinTimeOfService() {
		return minTimeOfService;
	}

	public synchronized int getMaxTimeOfService() {
		return maxTimeOfService;
	}

	public synchronized void setOpenTime(int min, int max) {
		minOpenTime = min;
		maxOpenTime = max;
	}

	public synchronized int getMinOpenTime() {
		return minOpenTime;
	}

	public synchronized int getMaxOpenTime() {
		return maxOpenTime;
	}

	public synchronized void setClosedTime(int min, int max) {
		minClosedTime = min;
		minClosedTime = max;
	}

	public synchronized int getMinClosedTime() {
		return minClosedTime;
	}

	public synchronized int getMaxClosedTime() {
		return maxClosedTime;
	}

	public synchronized void setNew(boolean b) {
		isNew = b;
	}

	public synchronized boolean isNew() {
		return isNew;
	}

	public synchronized void setSchedResponded(boolean b) {
		schedResponded = b;
	}

	public synchronized boolean hasSchedResponded() {
		return schedResponded;
	}

	public synchronized void setDecision(int[] d) {
		decision = d;
	}

	public synchronized int[] getDecision() {
		return decision;
	}

	public synchronized void setIsGui(boolean b) {
		isGui = b;
	}

	public synchronized boolean isGui() {
		return isGui;
	}

	public synchronized void setIsScheduling(boolean b) {
		isScheduling = b;
	}

	public synchronized boolean isScheduling() {
		return isScheduling;
	}

	private boolean isNew = false;
	private boolean schedResponded = false;
	private boolean isConnected = true;
	private boolean isGui = true;
	private boolean isScheduling = true;
	//private boolean isManualMode = false;
	private int newClients;
	private int minClientTime ;
	private int maxClientTime ;
	private int minTimeOfService;
	private int maxTimeOfService;
	private int minOpenTime;
	private int maxOpenTime;
	private int minClosedTime;
	private int maxClosedTime;
	private int[] decision;
	Timer timer;
	SimulationModel simulation;
}
