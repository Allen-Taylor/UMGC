/**
 * Vertex Class
 **/
package p4;

public class Vertex {
	private final String vertex;

	/**
	 * Vertex Constructor
	 * @param vertex A variable type of String
	 **/
	public Vertex(String vertex) {
		this.vertex = vertex;
	}

	/**
	 * Print Vertex String
	 * @return String The vertex label
	 **/
	@Override
	public String toString() {
		return vertex;
	}
}