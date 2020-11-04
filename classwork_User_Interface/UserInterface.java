package classwork_User_Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserInterface {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(400, 500);
		frame.setLayout(null); // Not using layout managers
		JButton button = new JButton("Click");
		button.setBounds(130, 50, 100, 40); // x, y, width, height
		
		frame.add(button);
		frame.setVisible(true); // Makes the frame visible
		
		JLabel l1 = new JLabel("First Label");
		l1.setBounds(130, 100, 100, 30);
		frame.add(l1);
		
		JLabel l2 = new JLabel("Second Label");
		l2.setBounds(130, 150, 100, 30);
		frame.add(l2);
		
		button.addActionListener(new ActionListener() {
			Random ran = new Random();
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 1E4; i++) {
					Math.sin(ran.nextDouble());
					l2.setText("Clicked: " + i);
					frame.setVisible(true);
				}
				
				for (int j = 0; j < 1E4; j++) {
					Math.sin(ran.nextDouble());
					l1.setText("New Text: " + 1);
					frame.setVisible(true);
				}
				frame.setVisible(true);
				System.out.println("BUTTON PRESS");
			}			
		});
	}

}
