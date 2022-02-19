/**
 * ParenthesizedList Class 
 */
package p4;

class ParenthesizedList<V> implements DFSActions<V> {

	String str = new String();
	
	/**
	 * Concatenate Vertex
	 * 
	 *@param vertex An object type of Vertex
	 */
	@Override
	public void processVertex(V vertex) {
		str += vertex.toString() + " ";
	}
	
	/**
	 * Concatenate (
	 */
	@Override
	public void descend() {
		str += "( ";
	}

	/**
	 * Concatenate )
	 */
	@Override
	public void ascend() {
		str += ") ";
	}

	/**
	 * Concatenate *
	 */
	@Override
	public void cycleDetected() {
		str += "* ";
	}
	
	/**
	 * Return ParenthesizedList String
	 * 
	 * @return String ParenthesizedList String
	 */
	@Override
	public String toString() {
		//Format String
		str = "( " + str + ")";
		str = str.replace("( * )", "*");
		str = str.replace("( ) ", "");
		str = str.replace("( *", "* (");
		return str;
	}
}
