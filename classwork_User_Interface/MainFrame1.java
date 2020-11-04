package classwork_User_Interface;

import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class MainFrame1 extends JFrame {
	private JLabel count1 = new JLabel();
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	
	public MainFrame1() {
		JFrame frame = new JFrame();
		frame.setSize(400, 500);
		frame.setLayout(null);
		JButton button1 = new JButton("Click");
		button1.setBounds(50, 50, 100, 40);
		JButton button2 = new JButton("Clack");
		button2.setBounds(250, 50, 100, 40);
		
		frame.add(button1);
		frame.add(button2);
		frame.setVisible(true);
		
		JLabel l1 = new JLabel("First Label");
		l1.setBounds(130, 150, 100, 30);
		JLabel l2 = new JLabel("Second Label");
		l2.setBounds(130, 200, 100, 30);
		JLabel l3 = new JLabel("Third Label");
		l3.setBounds(130, 250, 100, 30);
		JLabel l4 = new JLabel("Fourth Label");
		l4.setBounds(130, 300, 100, 30);
		
		frame.add(l1);
		frame.add(l2);
		frame.add(l3);
		frame.add(l4);
	}
	
	private void start() {
		SwingWorker<Void, Void> worker1 = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {
				for (int i = 0; i < 30; i++) {
					Thread.sleep(100);
					System.out.println("Hello: " + i);
				}
				return null;
			}
			
		};
		worker1.execute();
	}

	public JLabel getCount1() {
		return count1;
	}

	public void setCount1(JLabel count1) {
		this.count1 = count1;
	}

	public JLabel getL1() {
		return l1;
	}

	public void setL1(JLabel l1) {
		this.l1 = l1;
	}

	public JLabel getL2() {
		return l2;
	}

	public void setL2(JLabel l2) {
		this.l2 = l2;
	}

	public JLabel getL3() {
		return l3;
	}

	public void setL3(JLabel l3) {
		this.l3 = l3;
	}

	public JLabel getL4() {
		return l4;
	}

	public void setL4(JLabel l4) {
		this.l4 = l4;
	}
}
