package processing;

import java.awt.Color;
import java.util.Arrays;

import prj4.Edge;
import prj4.Tetrahedron;
import prj4.Vertex;
import prj4.VertexExtended;

/**
 * Transforming input for further processing
 * 
 * @author kacerekz
 */
public class InputProcessing {

	/**
	 * Gets unique edges from tetrahedron data
	 * @param data Tetrahedron data
	 * @return Unique edges
	 */
	public static Edge[] createEdges(Tetrahedron[] data) {
		Edge[] edges = EdgeProcessing.getEdges(data);
		Arrays.sort(edges);
		return EdgeProcessing.getUniqueEdges(edges);
	}
	
	/**
	 * Creates extended vertices from 
	 * @param Vertices
	 * @return Vertices with color data
	 */
	public static VertexExtended[] createVertices(Vertex[] vertices) {
		VertexExtended[] output = new VertexExtended[vertices.length];
		
		for (int i = 0; i < output.length; i++) {
			output[i] = new VertexExtended();
			output[i].v = vertices[i];
			output[i].color = Color.BLACK;
		}
		
		return output;
	}
	
}
