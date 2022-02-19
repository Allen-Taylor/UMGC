/**  
* GUI.java - GUI for creating a binary tree and executing checks on it
* 
* @author  Allen Taylor
* @course CMIS 350 6382 
* @assignment Project 3
* @date 2/12/2022
*/
package project3;

import java.awt.Dimension;
import java.awt.Font;
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

	static BinaryTree binaryTree = new BinaryTree();
	/**
	 * Main method.
	 */
	public static void main(String[] args) {

		JPanel contentPane;
		JTextField input;
		JTextField output;

		JFrame frame = new JFrame("Binary Tree Categorizer");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBounds(100, 100, 944, 214);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnMakeTree = new JButton("Make Tree");
		btnMakeTree.setBounds(10, 65, 120, 23);
		contentPane.add(btnMakeTree);

		JButton btnIsBalanced = new JButton("Is Balanced?");
		btnIsBalanced.setBounds(140, 65, 120, 23);
		contentPane.add(btnIsBalanced);

		JButton btnIsFull = new JButton("Is Full?");
		btnIsFull.setBounds(270, 65, 120, 23);
		contentPane.add(btnIsFull);

		JButton btnIsProper = new JButton("Is Proper?");
		btnIsProper.setBounds(400, 65, 120, 23);
		contentPane.add(btnIsProper);

		JButton btnHeight = new JButton("Height");
		btnHeight.setBounds(530, 65, 120, 23);
		contentPane.add(btnHeight);

		JButton btnNodes = new JButton("Nodes");
		btnNodes.setBounds(660, 65, 120, 23);
		contentPane.add(btnNodes);

		JButton btnInorder = new JButton("Inorder");
		btnInorder.setBounds(790, 65, 120, 23);
		contentPane.add(btnInorder);

		JLabel lblEnter = new JLabel("Enter Tree:");
		lblEnter.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnter.setBounds(140, 23, 67, 14);
		contentPane.add(lblEnter);

		input = new JTextField();
		input.setBounds(217, 20, 563, 20);
		contentPane.add(input);
		input.setColumns(10);

		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOutput.setBounds(140, 122, 67, 14);
		contentPane.add(lblOutput);

		output = new JTextField();
		output.setEditable(false);
		output.setColumns(10);
		output.setBounds(217, 119, 563, 20);
		contentPane.add(output);

		frame.setVisible(true);

		// Center GUI on Screen
		Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int iCoordX = (objDimension.width - frame.getWidth()) / 2;
		int iCoordY = (objDimension.height - frame.getHeight()) / 2;
		frame.setLocation(iCoordX, iCoordY);

		// Make Tree
		btnMakeTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText(""); 
				binaryTree.setOutputString(""); 
				try {
					String inputStr = input.getText();
					BinaryTree newBT = new BinaryTree(inputStr); 
					binaryTree = newBT; 
					JOptionPane.showMessageDialog(frame, "Binary Tree Created Successfully");
				} catch (InvalidTreeSyntax error) {
					JOptionPane.showMessageDialog(frame, error.toString());
				}

			}
		});

		// Is Balanced?
		btnIsBalanced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (binaryTree.getRoot() == null) {
					JOptionPane.showMessageDialog(frame, "Please make a tree before clicking this button.");
				} else {

					try {
						boolean isBalanced = binaryTree.isBalanced();
						output.setText(isBalanced+"");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frame, e1);
					}

				}

			}
		});

		// Is Full?
		btnIsFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (binaryTree.getRoot() == null) {
					JOptionPane.showMessageDialog(frame, "Please make a tree before clicking this button.");
				} else {

					try {
						boolean isFull = binaryTree.isFull();
						output.setText(isFull+"");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frame, e1);
					}

				}

			}
		});

		// Is Proper?
		btnIsProper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (binaryTree.getRoot() == null) {
					JOptionPane.showMessageDialog(frame, "Please make a tree before clicking this button.");
				} else {

					try {
						boolean isProper = binaryTree.isProper();
						output.setText(isProper+"");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frame, e1);
					}

				}
			}
		});

		// Height
		btnHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (binaryTree.getRoot() == null) {
					JOptionPane.showMessageDialog(frame, "Please make a tree before clicking this button.");
				} else {

					try {
						int height = binaryTree.height();
						output.setText(height+"");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frame, e1);
					}

				}

			}
		});

		// Nodes
		btnNodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (binaryTree.getRoot() == null) {
					JOptionPane.showMessageDialog(frame, "Please make a tree before clicking this button.");
				} else {

					try {
						int nodes = binaryTree.numNodes();
						output.setText(nodes+"");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frame, e1);
					}

				}

			}
		});

		// In Order
		btnInorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (binaryTree.getRoot() == null) {
					JOptionPane.showMessageDialog(frame, "Please make a tree before clicking this button.");
				} else {
					output.setText(""); 
					binaryTree.setOutputString(""); 
					try {
						binaryTree.inOrder(); 
						output.setText(binaryTree.getOutputString().replaceAll(" ", "")); 
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frame, e1);
					}

				}
			}
		});

	}

}
