package prj4;

/**
 * Creates a sortable edge
 * 
 * @author kacerekz
 */
public class Edge implements Comparable<Edge> {

	/** Edge vertices */
	public Vertex v1, v2;
	
	/**
	 * Creates a new Edge
	 * @param v1 Vertex 1
	 * @param v2 Vertex 2
	 */
	public Edge(Vertex v1, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	/**
	 * Compares two edges
	 */
	@Override
	public int compareTo(Edge o) {
		if (v1.id < o.v1.id) 
			return -1;
		if (v1.id > o.v1.id)
			return 1;
		if (v2.id < o.v2.id)
			return -1;
		if (v2.id > o.v2.id)
			return 1;
		return 0;
	}

	/**
	 * Creates a string representation of an Edge (a line as per .obj specification)
	 */
	@Override
	public String toString() {
		return String.format("l %d %d", v1.id, v2.id);
	}

}
