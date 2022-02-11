
/**  
* Project1.java - GUI used to convert Prefix to Postfix or Postfix to Prefix.    
* 
* @author  Allen Taylor
* @course CMIS 350 6382 
* @date 1/15/2022
*/

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI {

	/**
	 * This method is used to convert an expression from Prefix to Postfix.
	 * 
	 * @param input  A variable type of JTextField
	 * @param output A variable type of JTextField
	 * @exception SyntaxError On syntax error
	 */
	private static void preToPost(JTextField input, JTextField output) throws SyntaxError {

		String exp = input.getText();
		if (input.getText().isEmpty() == false) {

			try {
				PreToPost p2p = new PreToPost(exp);
				output.setText(p2p.getConversion());
			} catch (Exception e) {
				output.setText("ERROR");
				throw new SyntaxError("\n" + e.toString());
			}

		} else {
			output.setText("Please enter an expression.");
		}

	}

	/**
	 * This method is used to convert an expression from Postfix to Prefix.
	 * 
	 * @param input  A variable type of JTextField
	 * @param output A variable type of JTextField
	 * @throws SyntaxError On syntax error
	 */
	private static void postToPre(JTextField input, JTextField output) throws SyntaxError {

		String exp = input.getText();
		if (input.getText().isEmpty() == false) {
			try {
				PostToPre p2p = new PostToPre(exp);
				output.setText(p2p.getConversion());
			} catch (Exception e) {
				output.setText("ERROR");
				throw new SyntaxError("\n" + e.toString());
			}

		} else {
			output.setText("Please enter an expression.");
		}

	}

	/**
	 * Main method
	 */
	public static void main(String[] args) {
		JPanel contentPane;
		JTextField input;
		JTextField output;

		JFrame frame = new JFrame("Expression Convertor");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 320, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel inputLabel = new JLabel("Enter expression:");
		inputLabel.setBounds(24, 11, 256, 14);
		contentPane.add(inputLabel);

		input = new JTextField();
		input.setBounds(24, 36, 265, 20);
		contentPane.add(input);
		input.setColumns(10);

		JButton preToPostButton = new JButton("Prefix to Postfix");
		preToPostButton.setBounds(24, 70, 125, 23);
		contentPane.add(preToPostButton);

		JButton postToPreButton = new JButton("Postfix to Prefix");
		postToPreButton.setBounds(162, 70, 125, 23);
		contentPane.add(postToPreButton);

		JLabel resultLabel = new JLabel("Result:");
		resultLabel.setBounds(24, 109, 256, 14);
		contentPane.add(resultLabel);

		output = new JTextField();
		output.setBounds(24, 129, 265, 20);
		output.setEditable(false);
		contentPane.add(output);
		output.setColumns(10);

		frame.setVisible(true);

		// Center GUI on Screen
		Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int iCoordX = (objDimension.width - frame.getWidth()) / 2;
		int iCoordY = (objDimension.height - frame.getHeight()) / 2;
		frame.setLocation(iCoordX, iCoordY);

		preToPostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					preToPost(input, output);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1);
				}

			}
		});

		postToPreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					postToPre(input, output);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1);
				}

			}
		});
	}

}
