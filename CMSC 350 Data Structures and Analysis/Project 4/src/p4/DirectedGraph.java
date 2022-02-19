/**
 * DirectedGraph Class (Generic)
 **/
package p4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DirectedGraph<V> {

	private List<V> vertices = new ArrayList<V>();
	private List<LinkedList<V>> edges = new ArrayList<LinkedList<V>>();

	private boolean cycle = false;

	private Set<V> dfsDiscovered = new HashSet<>();
	private Set<V> dfsVisited = new HashSet<>();

	private ParenthesizedList<Vertex> parenthesizedList = new ParenthesizedList<Vertex>();
	private Hierarchy<Vertex> hierarchy = new Hierarchy<Vertex>();

	/**
	 * Adds a Vertex to an ArrayList of Vertices 
	 * & adds a new LinkedList to Edges List
	 * 
	 * @param vertex An object type of Vertex
	 **/
	public void addVertex(V vertex) {
		vertices.add(vertex);
		edges.add(new LinkedList<V>());
	}

	/**
	 * Get Vertex based on index
	 * 
	 * @param i A variable type of int
	 * @return V A Vertex from the ArrayList
	 **/
	public V getVertex(int i) {
		return vertices.get(i);
	}

	/**
	 * Get Vertex based on vertex string
	 * 
	 * @param vertexString A variable type of String
	 * @return V A Vertex from the Vertices ArrayList
	 **/
	public V getVertex(String vertexString) {
		for (V v : getVertices()) {
			if (v.toString().equals(vertexString)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Get Vertex based on Vertex object
	 * 
	 * @param vertex An object type of Vertex
	 * @return V A Vertex from the Vertices ArrayList
	 **/
	public V getVertex(V vertex) {
		for (V vert : getVertices()) {
			if (vert == vertex) {
				return vert;
			}
		}
		return null;
	}

	/**
	 * Get Vertices
	 * 
	 * @return List<V> List of Vertices
	 **/
	public List<V> getVertices() {
		return vertices;
	}

	/**
	 * Get Vertex indexOf
	 * 
	 * @param v An object type of Vertex
	 * @return int The Vertex Index
	 **/
	public int getVertexIndex(V v) {
		return vertices.indexOf(v);
	}

	/**
	 * Adds an Edge to Edge List based on Source and Destination Vertices
	 * 
	 * @param source      An object type of Vertex
	 * @param destination An object type of Vertex
	 **/
	public void addEdge(V source, V destination) {
		edges.get(getVertexIndex(source)).add(destination);
	}

	/**
	 * Get Edge based on index 
	 * 
	 * @param i A variable type of int
	 * @return LinkedList<V> Edges for a specific Vertex
	 **/
	public LinkedList<V> getEdge(int i) {
		return edges.get(i);
	}

	/**
	 * Get Edge based on Vertex
	 * 
	 * @param v An object type of Vertex
	 * @return LinkedList<V> Edges for a specific Vertex
	 **/
	public LinkedList<V> getEdge(V v) {
		return edges.get(getVertexIndex(v));
	}

	/**
	 * Get Edges
	 * 
	 * @return List<LinkedList<V>> A list of all Edges (LinkedList<Vertex>)
	 **/
	public List<LinkedList<V>> getEdges() {
		return edges;
	}

	/**
	 * Get Cycle boolean
	 * 
	 * @return boolean Check for cycle
	 **/
	public boolean getCycle() {
		return cycle;
	}

	/**
	 * Set Cycle boolean
	 * 
	 * @param cycle A variable type of boolean
	 **/
	public void setCycle(boolean cycle) {
		this.cycle = cycle;
	}

	/**
	 * Get List of visited Vertices
	 * 
	 * @return Set<V> List of visited Vertices
	 **/
	public Set<V> getDfsVisited() {
		return dfsVisited;
	}

	/**
	 * Print Parenthesized List Representation of Directed Graph
	 **/
	public void printParenthesizedList() {
		System.out.println("Parenthesized List Representation:");
		System.out.println(parenthesizedList.toString() + "\n");
	}

	/**
	 * Print Hierarchy Representation of Directed Graph
	 **/
	public void printHierarchy() {
		System.out.println("Hierarchy Representation:");
		System.out.println(hierarchy.toString() + "\n");
	}

	/**
	 * Print Unreachable Classes within Directed Graph
	 **/
	public void printUnreachableClasses() {
		Set<V> discovered = getDfsVisited();
		List<V> verts = getVertices();
		verts.removeAll(discovered);
		System.out.println("The following classes are unreachable:");
		if (verts.size() > 0) {
			verts.forEach(v -> System.out.println(v));
		} else {
			System.out.println("None");
		}

	}

	/**
	 * Depth First Search Helper 
	 **/
	public void depthFirstSearch() {
		setCycle(false);
		dfs(getVertex(0));
	}

	/**
	 * Depth First Search Recursion
	 * 
	 * @param v An object type of Vertex
	 **/
	private void dfs(V v) {
		if (dfsDiscovered.contains(v)) {
			setCycle(true);

			parenthesizedList.cycleDetected();
			hierarchy.cycleDetected();

			return;
		}

		parenthesizedList.processVertex((Vertex) v);
		hierarchy.processVertex((Vertex) v);

		parenthesizedList.descend();
		hierarchy.descend();

		dfsVisited.add(v);
		dfsDiscovered.add(v);

		LinkedList<V> edge = getEdge(v);
		if (edge != null) {
			for (V vertex : edge)
				dfs(getVertex(vertex));
		}

		parenthesizedList.ascend();
		hierarchy.ascend();

		dfsDiscovered.remove(v);
	}

}