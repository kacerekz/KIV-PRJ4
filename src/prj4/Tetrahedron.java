package prj4;

/**
 * Represents a tetrahedron specified by its vertices
 * 
 * @author kacerekz
 */
public class Tetrahedron {

	/** Tetrahedron vertices */
	public Vertex[] vertices;
	
	/**
	 * Creates a tetrahedron given its vertices
	 * @param v1 Vertex 1
	 * @param v2 Vertex 2
	 * @param v3 Vertex 3
	 * @param v4 Vertex 4
	 */
	public Tetrahedron(Vertex v1, Vertex v2, Vertex v3, Vertex v4) {
		this.vertices = new Vertex[] {v1, v2, v3, v4};
	}
	
	/**
	 * Returns tetrahedron info as a string
	 */
	@Override
	public String toString() {
		return String.format("%d %d %d %d", vertices[0].id, vertices[1].id, vertices[2].id, vertices[3].id);
	}
		
}
