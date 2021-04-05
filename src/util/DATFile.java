package util;

import prj4.Tetrahedron;
import prj4.Vertex;

/**
 * DAT file data container
 * @author kacerekz
 */
public class DATFile {
	
	/** Vertices */
	public Vertex[] vertices;

	/** Tetrahedrons */
	public Tetrahedron[] tetrahedra;

	/**
	 * Creates the data container
	 * @param vertices Vertex data
	 * @param tetrahedra Tetrahedron data
	 */
	public DATFile(Vertex[] vertices, Tetrahedron[] tetrahedra) {
		this.vertices = vertices;
		this.tetrahedra = tetrahedra;
	}

}
