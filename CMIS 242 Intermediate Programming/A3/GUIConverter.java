/**  
* GUIConverter.java - GUI code used in Temperature and Distance Converter.    
* 
* @author  Allen Taylor
* @course CMIS 242 7384 
* @date 11/27/2021
*/

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUIConverter {

	public void createGui() {

		// Create GUI Components
		JFrame frame = new JFrame("Temperature and Distance Converter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel buttonPanel1 = new JPanel();
		JPanel buttonPanel2 = new JPanel();
		JPanel buttonPanel3 = new JPanel();
		buttonPanel1.setLayout(new GridLayout(1, 2));
		buttonPanel2.setLayout(new GridLayout(1, 1));
		buttonPanel3.setLayout(new GridLayout(2, 1));
		JButton tcButton = new JButton("Temperature Converter");
		JButton dcButton = new JButton("Distance Converter");
		JButton exitButton = new JButton("Exit");
		buttonPanel1.add(tcButton);
		buttonPanel1.add(dcButton);
		buttonPanel2.add(exitButton);
		buttonPanel1.setPreferredSize(new Dimension(500, 50));
		buttonPanel2.setPreferredSize(new Dimension(500, 50));
		buttonPanel3.add(buttonPanel1);
		buttonPanel3.add(buttonPanel2);
		frame.add(buttonPanel3);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);

		// TC Button
		tcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temperatureConverter(frame);
			}
		});

		// DC Button
		dcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				distanceConverter(frame);
			}
		});

		// Exit Button
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Exit
			}
		});

		// Center Program on Screen
		Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int iCoordX = (objDimension.width - frame.getWidth()) / 2;
		int iCoordY = (objDimension.height - frame.getHeight()) / 2;
		frame.setLocation(iCoordX, iCoordY);

	}

	// temperatureConverter Button Click Method 
	private static void temperatureConverter(JFrame frame) {
		String temp;
		while (true) { // Input Validation Loop
			temp = JOptionPane.showInputDialog("Enter a temperature in Fahrenheit: "); // Accept input
			if ((temp != null) && (temp.length() > 0)) { // Check if input is NULL or a 0 length string
				Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]"); // REGEX Non-Numbers
				Matcher m = p.matcher(temp); // Create matcher
				if (m.find()) { // Search if there is a match, throw error message
					JOptionPane.showMessageDialog(null, "Invalid input. Please enter only numbers.");
				} else { // If there are numbers, proceed with program
					Double tempDouble = Double.valueOf(temp); // Cast string to Double
					Converter tc = new TemperatureConverter(tempDouble); // Create Instance of Temperature Converter
					Double result = tc.convert(); // Convert Temperature and return the result
					DecimalFormat df = new DecimalFormat("###.##"); // Limit the decimal places to 2
					JOptionPane.showMessageDialog(frame, temp + " \u00B0 F = " + df.format(result) + " \u00B0 C"); 
					break; // Break out of Input Validation loop
				}
			} else {
				break;
			}  
		}
	}

	// distanceConverter Button Click Method 
	private static void distanceConverter(JFrame frame) {
		String dist;
		while (true) {
			dist = JOptionPane.showInputDialog("Enter the distance in miles:"); // Accept input
			if ((dist != null) && (dist.length() > 0)) { // Check if input is NULL or a 0 length string
				Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]"); // REGEX Non-Numbers
				Matcher m = p.matcher(dist); // Create matcher
				if (m.find()) { // Search if there is a match, throw error message
					JOptionPane.showMessageDialog(null, "Invalid input. Please enter only numbers.");
				} else { // If there are numbers, proceed with program
					Double distDouble = Double.valueOf(dist); // Cast string to Double
					Converter dc = new DistanceConverter(distDouble); // Create Instance of Distance Converter
					Double result = dc.convert(); // Convert Distance and return the result
					DecimalFormat df = new DecimalFormat("###.##"); // Limit the decimal places to 2
					JOptionPane.showMessageDialog(frame, dist + " miles = " + df.format(result) + " kilometers"); 
					break; // Break out of Input Validation loop
				}
			} else {
				break;
			}  
		}
	}

}
