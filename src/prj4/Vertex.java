package prj4;

/**
 * Represents a vertex
 * 
 * @author kacerekz
 */
public class Vertex {

	/** Vertex ID */
	public int id;
	
	/** Vertex coordinates */
	public double x, y, z;

	/**
	 * Creates a vertex with given coordinates
	 * @param x X axis coordinate
	 * @param y Y axis coordinate
	 * @param z Z axis coordinate
	 */
	public Vertex(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
