/**
 * Hierarchy Class 
 */
package p4;

import java.util.ArrayList;

public class Hierarchy<V> implements DFSActions<V> {
	int indent = 0;
	String hierarchyString = new String();
	ArrayList<String> classList = new ArrayList<String>();

	/**
	 * Process Vertex
	 * 
	 *@param vertex An object type of Vertex
	 */
	@Override
	public void processVertex(V vertex) {
		classList.add(indentation(indent)+vertex.toString());
	}

	/**
	 * Increment the indentation count
	 */
	@Override
	public void descend() {
		indent += 4;
	}

	/**
	 * Decrement the indentation count
	 */
	@Override
	public void ascend() {
		indent -= 4;
	}

	/**
	 * Add * to the last processed Vertex
	 */
	@Override
	public void cycleDetected() {
		classList.set(classList.size() - 1, classList.get(classList.size() - 1) + " *");
	}

	/**
	 * Return Hierarchy String
	 * 
	 * @return String Hierarchy String
	 */
	@Override
	public String toString() {
		classList.forEach(c -> hierarchyString = hierarchyString + c + "\n");
		return hierarchyString;
	}

	/**
	 * Create indentation
	 * 
	 * @param int A variable type of size
	 * @return String White space indentation
	 */
	private static String indentation(int size) {
		StringBuilder sb = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}

}
