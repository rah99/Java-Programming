package supermarketQueues;

import java.io.*;
import java.util.LinkedList;


public class msg implements Serializable {
	private String wiadomosc;
	private String ID;
	private String TO;
	public int odklienta1;
	public int odklienta2;
	public int odklienta3;
	
	//od modulu symulacji
	public BoxOffice [] Kasy= new BoxOffice [10];
	public LinkedList<Client> zakasami;
	public LinkedList<Client> przedkasami;
	public int czasSymulacji;
	public int nowiklienci;
	public int [] ileWKasie=new int [10];
	public int [] stany = new int [10];
	public int[][] Idklientow=new int [10][20];
	public  int [] czasobslugi=new int[10];
	public int[] idklientowprzedkasami=new int[100];
	public  int[] idklientowzakasami=new int[100];
	// od modulu zarzadzania
	public int [] decyzja=new int [10];
	public boolean isSchedulingOn;
	// od modulu gui
	public boolean isGuiOn;
	public SimuMode tryb;
	public boolean isStarted;
	public boolean isHalted;
	public boolean isNext;
	public int klienci;
	public int minczaspojawianiaklienta ;
	public int maxczaspojawianiaklienta ;
	public int minczasobslugiklienta;
	public int maxczasobslugiklienta;
	public int minczasotwarciakasy;
	public int maxczasotwarciakasy;
	public int minczaszamknieciakasy;
	public int maxczaszamknieciakasy;
	public BoxOfficeState [] stanykas= new BoxOfficeState[10];
	 public int[] stanykasint=new int[10];
	
	
	//wiadomosci protokolu wykorzystywane przez serwer i klienta
	public msg(String i, String string, String adresat) {
		ID =i;
		wiadomosc=string;
		TO = adresat;
	}
	
	//wiadomosci od modelu symulacji do gui 
	//i - numer modulu wysy³aj¹cego : 1-symulacja 2-zarzadzanie 3-gui
	//adresat wiadomosci : 1-symulacja 2-zarzadzanie 3-gui
	public msg(String i, String adresat,  BoxOffice[] kasysymulacji, int[] klienciwkasach,int[] stany, int[][] Idklientow, int [] czasobslugi, LinkedList<Client> PrzedKasami,int[] idklientowprzedkasami, LinkedList<Client> ZaKasami,int[] idklientowzakasami, int czas )
	{
		this.ID=i;
		this.TO=adresat;
		this.Kasy=kasysymulacji;
		this.stany=stany;
		this.ileWKasie=klienciwkasach;
		this.Idklientow=Idklientow;
		this.czasobslugi=czasobslugi;
		this.idklientowprzedkasami=idklientowprzedkasami;
		this.idklientowzakasami=idklientowzakasami;
		this.zakasami=ZaKasami;
		this.przedkasami=PrzedKasami;
		this.czasSymulacji=czas;
	}
	//wiadomosci od modelu symulacji do modelu zarzadzania 
		//i - numer modulu wysy³aj¹cego : 1-symulacja 2-zarzadzanie 3-gui
		//adresat wiadomosci : 1-symulacja 2-zarzadzanie 3-gui
		public msg(String i, String adresat,  BoxOffice[] kasysymulacji, int nowiKlienci, int[] iloscwkasach, int stan[])
		{
			this.ID=i;
			this.TO=adresat;
			this.Kasy=kasysymulacji;
			this.nowiklienci = nowiKlienci;
			this.ileWKasie=iloscwkasach;
			this.stany=stan;
		}
		
	//wiadomosci od modelu zarzadzania do modelu symulacji
	//i - numer modulu wysy³aj¹cego : 1-symulacja 2-zarzadzanie 3-gui
	//adresat wiadomosci : 1-symulacja 2-zarzadzanie 3-gui
	public msg(String i, String adresat, int [] dec)
	{
	this.ID=i;	
	this.TO=adresat;	
	this.decyzja=dec;
	}
	//wiadomosci od gui do modelu symulacji
	//i - numer modulu wysy³aj¹cego : 1-symulacja 2-zarzadzanie 3-gui
	//adresat wiadomosci : 1-symulacja 2-zarzadzanie 3-gui
	 public msg(String i, String adresat, boolean isStarted, boolean isHalted, boolean isNext, SimuMode mode,int nowiKlienci, int minPK, int maxPK, int minOK, int maxOK, int minOTWK, int maxOTWK,int minZAMK, int maxZAMK, BoxOfficeState[] stany,int[] stanyint)
		{
		this.ID=i;	
		this.TO=adresat;	
		this.tryb=mode;
		this.isStarted=isStarted;
		this.isHalted=isHalted;
		this.isNext=isNext;
		this.klienci=nowiKlienci;
		this.minczaspojawianiaklienta=minPK;
		this.minczasobslugiklienta=minOK;
		this.maxczaspojawianiaklienta=maxPK;
		this.maxczasobslugiklienta=maxOK;
		this.minczasotwarciakasy=minOTWK;
		this.maxczasotwarciakasy=maxOTWK;
		this.minczaszamknieciakasy=minZAMK;
		this.maxczaszamknieciakasy=maxZAMK;
		this.stanykas=stany;
		 this.stanykasint=stanyint;
		}
	/**
	 * Odczytanie wlasciwej wiadomosci.
	 * @return wiadomosc
	 */
	public String getMessage() {
		return wiadomosc;
	}
	
	/**
	 * Ustawienie wlasciwej wiadomosci.
	 * @param msg wiadomosc do ustawienia
	 */
	public void setMessage(String msg) {
		wiadomosc = msg;
	}
	
	/**
	 * Odczytanie ID 
	 * @return ID 
	 */
	public String getID() {
		return ID;
	}
	public String getTO() {
		return TO;
	}
	/**
	 * Ustawienie ID .
	 * @param id ID 
	 */
	public void setID(String id) {
		ID = id;
	}
	public enum SimuMode {
		 /** Tryb automatyczny.*/
		 AUTOMATIC,
		 /** Tryb reczny .*/
		 MANUAL,
		 
	}
}