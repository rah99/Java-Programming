package nuclearReactor;

public class SteamProduction extends Thread{
	private double ControlTemp = 0;
	private double WaterCurrent = 0;
	private double Temp = 0;
	private double SteamProduced;
	private double Steam;

	public SteamProduction(double temp ,double controlTemp ,double steamProduced, double steam) {
		this.Temp = temp;
		this.ControlTemp = controlTemp;
		this.SteamProduced = steamProduced;
		this.Steam = steam;
	}

	public void run() {
		while(true) {
			while(Temp >= ControlTemp) {
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
	
	public double getSteamProduced() {
		return SteamProduced;
	}

	public void setSteamProduced(double steamProduced) {
		SteamProduced = steamProduced;
	}

	public double getSteam() {
		return Steam;
	}

	public void setSteam(double steam) {
		Steam = steam;
	}

	public double getControltemp() {
		return ControlTemp;
	}

	public double getWatercurrent() {
		return WaterCurrent;
	}

	public double getTemp() {
		return Temp;
	}

}
