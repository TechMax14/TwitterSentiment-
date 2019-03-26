import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class GUI extends JFrame {
	
	static JTextArea twitterWindow = new JTextArea();
	JTextField searchFor = new JTextField();
	JTextField range = new JTextField();
	JLabel label = new JLabel();
//////

	public GUI() {
		super("Tweet Analyzer");	
		add(new JScrollPane(twitterWindow)); //M-F, 1-5, vin number, registration card
		setSize(1000, 500); //Sets the window size
		setVisible(true);
		Object[] fields = {
				"Only you know what to search for:", label,
				"What would you like to search twitter for?", searchFor,
				"How many tweets should we attain?", range
		};
		JOptionPane.showConfirmDialog(null, fields, "Twitter Query", JOptionPane.OK_CANCEL_OPTION);

	}
	
	static void showMessage(final String message){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					twitterWindow.append(message);
				}
			}
		);

	}
	
}
