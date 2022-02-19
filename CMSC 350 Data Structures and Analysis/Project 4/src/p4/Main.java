/**  
* Main.java - Main program for reading in Directed Graph and outputting two string representations 
* 
* @author  Allen Taylor
* @course CMIS 350 6382 
* @assignment Project 4
* @date 2/19/2022
*/

package p4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main {

	// Directed Graph Object
	static DirectedGraph<Vertex> dg = new DirectedGraph<Vertex>();

	/**
	 * Method used to produce a Directed Graph
	 */
	public static void createDirectedGraph() {

		TreeSet<String> verticesTreeSet = new TreeSet<String>();
		ArrayList<String[]> linesArrayList = new ArrayList<String[]>();
		
		// Read input file and parse lines
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int response = fc.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				Scanner scanner = new Scanner(file);
				if (file.isFile()) {
					
					// Create an ArrayList of Arrays (lines)
					while (scanner.hasNextLine()) {
						String vertex = scanner.nextLine();
						String[] vertexArray = vertex.split(" ");
						linesArrayList.add(vertexArray);
					}
					
					// Add Vertices Strings to TreeSet
					for (String[] vertexArray : linesArrayList) {
						for (int i = 0; i < vertexArray.length; i++) {
							verticesTreeSet.add(vertexArray[i]); 
						}
					}

					// Add Vertices to Directed Graph
					verticesTreeSet.forEach(vertexString -> dg.addVertex(new Vertex(vertexString)));

					// Add Edges to Directed Graph
					for (String[] vertexArray : linesArrayList) {
						for (int i = 1; i < vertexArray.length; i++) {
							Vertex source = dg.getVertex(vertexArray[0]);
							Vertex destination = dg.getVertex(vertexArray[i]);
							dg.addEdge(source, destination);
						}
					}

					scanner.close();
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Error: Please try again.");
			}
		}
	}

	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			createDirectedGraph();
			dg.depthFirstSearch();
			dg.printParenthesizedList();
			dg.printHierarchy();
			dg.printUnreachableClasses();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
					"Error creating the Directed Graph. Please verify the input file.");
		}
	}

}