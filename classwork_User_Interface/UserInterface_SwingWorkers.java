package classwork_User_Interface;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class UserInterface_SwingWorkers {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame1();
			}
		});
	}
}
