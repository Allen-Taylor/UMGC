
/**  
* Main.java - Main program for reading in Polynomial files and determining Strong/Weak Ordering
* 
* @author  Allen Taylor
* @course CMIS 350 6382 
* @assignment Project 2
* @date 2/4/2022
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main {
	public static List<Polynomial> polynomialList = new ArrayList<>();

	/**
	 * Method reads in text file of polynomials and returns a List
	 * 
	 * @return List<String> Returns a list of polynomials.
	 */
	private static List<String> readFile() {

		List<String> polyArrayList = new ArrayList<>();
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int response = jfc.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			try {
				Scanner scanner = new Scanner(file);
				if (file.isFile()) {
					while (scanner.hasNextLine()) {
						String expression = scanner.nextLine();
						polyArrayList.add(expression);
					}
				}
				scanner.close();
			} catch (NoSuchElementException nse) {
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "ERROR: File Empty.");
			} catch (FileNotFoundException fne) {
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "ERROR: File not found.");
			}
		}

		return polyArrayList;
	}

	/**
	 * Checks for Weak Ordering within a list of Polynomials
	 * 
	 * @return boolean Returns true/false
	 */
	private static boolean checkOrder(List<Polynomial> polyList) throws InvalidPolynomialSyntax {

		List<Integer> firstTerms = new ArrayList<>();
		for (Polynomial p : polynomialList) {
			List<Integer> tempArray = p.getExponents();
			Integer tempInt = tempArray.get(0);
			firstTerms.add(tempInt);
		}

		if (isSorted(firstTerms)) {
			return true;
		}

		return false;
	}

	/**
	 * Helper method used by checkOrder to determine if list of exponents is in order.
	 * 
	 * @return boolean Returns true/false
	 */
	private static <T extends Comparable<Integer>> boolean isSorted(List<Integer> firstTerms) {
		for (int i = 1; i < firstTerms.size(); i++)
			if (firstTerms.get(i - 1).compareTo(firstTerms.get(i)) > 0)
				return false;
		return true;
	}

	/**
	 * Main method.
	 */
	public static void main(String[] args) throws InvalidPolynomialSyntax {

		try {
			List<String> polyStrings = readFile();

			for (String polyStr : polyStrings) {
				Polynomial p;
				try {
					p = new Polynomial(polyStr);
					polynomialList.add(p);

				} catch (InvalidPolynomialSyntax e) {
					JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), e.toString());
				}
			}

			if (polynomialList.size() != 0) {
				
				System.out.println("=============");
				System.out.println(" POLYNOMIALS ");
				System.out.println("=============");
				for (Polynomial p : polynomialList) {
					System.out.println(p);
				}
				System.out.println("\n");
				System.out.println("=============");
				System.out.println("   RESULTS   ");
				System.out.println("=============");
				
				System.out.println("Strong Ordered: " + OrderedList.checkSorted(polynomialList));
				System.out.println("Weak Ordered: " + checkOrder(polynomialList));
			} else {
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "ERROR: No Polynomials found.");
			}

		} catch (InvalidPolynomialSyntax e) {
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), e.getMessage());
		}

	}

}
