package nuclearReactor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Reactor {
	//These are the key variable of the reactor system that will change and fluctuate to keep the system at a high temperature in order to produced the maximum amount of steam
	public static double Steam = 0.00;
	public static double SteamProduced = 0.00;
	//Please take notice that the temperature starts at 20 degrees, though the brief says to start at 0 this would stop the system form running as it all uses % to run and any % of 0 is 0 - thus temp would not increase
	public static double Temp = 20.00;
	public static double Regulators = 10.00;
	//The min, max and current water levels are all arbitrary as there was no data specified 
	public static double WaterMax = 100000.00;
	public static double WaterMin = 10000.00;
	public static double WaterCurrent = 50000.00;
	public static double WaterIntake = 0.00;
	public static double ControlRods = 100.00;
	public static double FuelRods = 200.00;
	public static double ControlTemp = 100.00;

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creates the classes for the Running elements
		class WaterRegulation extends Thread{
			public void run() {
				while(true) {
					//This takes the regulators and works out how much water the system is increasing by
					double Regs = Regulators;
					double WaterIn = WaterMax * (Regs/100);
					WaterIntake = WaterIn;
					System.out.println("Water Intake:"+WaterIn);
					WaterCurrent = WaterCurrent + WaterIn;
					System.out.println("Current Water level:"+WaterCurrent);
					//This then waits a second before repeating the calculations as per the brief

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//This is here so that the regulators can be applied in order to keep The maximum amount of steam to be produced - this varies on both temperature and the amount of water is already in the system
					if (Temp <= 100){
						Regulators = 1.00;
					} else if(Temp > 350 || WaterCurrent < WaterMin) {
						break;
					}else if(Temp >=300 || WaterCurrent < (3 * WaterMin)) {
						Regulators = 10.00;
					} else if (Temp >= 200 || WaterCurrent < (5 * WaterMin)) {
						Regulators = 10.00;
					} else if(Temp >= 100 || WaterCurrent < (7 * WaterMin)) {
						Regulators = 7.00;
					} else{
						break;
					}
					//This manages the maximum water that is in the system to prevent the water from increasing over the maximum and reducing over the minimum
					if (WaterCurrent > WaterMax ) {
						WaterCurrent = WaterMax;
					} else if (WaterCurrent < WaterMin) {
						break;
					} else {

					}
				}
			}


		}
		class ControlRods extends Thread{
			public void run() {
				while(true) {
					//This calculate the temp that the reactor is being reduced by
					double TempReduction = 0.00;
					TempReduction = Temp *((ControlRods/100)/100);
					Temp = Temp - TempReduction;
					//This then waits a tenth of a second before repeating the calculations as per the brief
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//This alters the amount of Control rods that are in the reactor in order for the temp to not go above 350

					if (Temp < 100){
						ControlRods = 0.00;
					} else if(Temp > 350) {
						//breaks if it goes over temp
						break;
					}else if(Temp >=300) {
						ControlRods = 100.00;
					} else if (Temp >= 200) {
						ControlRods = 75.00;
					} else if(Temp >= 100) {
						ControlRods = 0.00;
					} else{
						break;
					}
				}
			}


		}
		class FuelRods extends Thread{
			public void run() {
				while(true) {
					//This increase the temperature of the reactor based on the number of fuel rods
					double Fuel = 0.001 * FuelRods;
					double NewTemp = 0.000;
					NewTemp = Temp + (Temp * Fuel);
					Temp = Temp + (Temp * Fuel);
					setTemp(Temp);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//This alters the amount of fuel rods that are in the reactor in order for the temp to not go above 350
					if (Temp < 100){
						FuelRods = 200.00;
					} else if(Temp > 350) {
						//breaks if it goes over temp
						break;
					}else if(Temp >=300) {
						FuelRods = 75.00;
					} else if (Temp >= 200) {
						FuelRods = 150.00;
					} else if(Temp >= 100) {
						FuelRods = 200.00;
					} else {
						break;
					}
				}
			}


		}
		
		class SteamProduction extends Thread{
			public void run() {
				while(true) {
					while(Temp >= ControlTemp) {
						System.err.println(Temp);
						double SteamPro = 0.00;
						//takes the temperature and gets 20% of it when it is over 100 
						SteamPro = Temp / 2000;
						//This works out the steam produced and reduces the water accordingly
						SteamPro = WaterCurrent * SteamPro;
						WaterCurrent = WaterCurrent - SteamPro;
						SteamProduced = SteamPro;
						Steam = Steam + SteamPro;
						//This then waits a second before repeating the calculations as per the brief
						try {
							Thread.sleep(1000);

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					}

				}
			}


		}
		
		class InformationOutput extends Thread{
			public void run() {
				while(true) {
					//This is where the System data is displayed to the program user
					System.out.println("--------------------Information Update--------------------");
					System.out.println("Current Temp:" + Temp);
					System.out.println("Water Intake:" + WaterIntake);
					System.out.println("Current Water level:" + WaterCurrent);
					System.out.println("Steam Produced:"+ SteamProduced);
					System.out.println("The Total Steam Produced:" + Steam);
					System.out.println("Fuel Rods Active:" + FuelRods);
					System.out.println("Control Rods Active:" + ControlRods);
					System.out.println("Regulatores Active:" + Regulators);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}


		}
		//This Starts the systems for the user
		SteamProduction S = new SteamProduction();
		S.start();
		ControlRods C = new ControlRods();
		C.start();
		WaterRegulation R = new WaterRegulation();
		R.start();
		FuelRods F = new FuelRods();
		F.start();
		InformationOutput Info = new InformationOutput();
		Info.start();
		
//		ExecutorService executor = Executors.newFixedThreadPool(5);
//		executor.execute(new SteamProduction());
//		executor.execute(new ControlRods());
//		executor.execute(new WaterRegulation());
//		executor.execute(new FuelRods());
//		executor.execute(new InformationOutput());
	}
	
	public static double getTemp() {
		return Temp;
	}
	
	public static void setTemp(double temp) {
		Temp = temp;
	}

}
