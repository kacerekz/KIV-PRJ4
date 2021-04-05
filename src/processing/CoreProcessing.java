package processing;

import prj4.Edge;
import prj4.Vertex;
import prj4.VertexExtended;

/**
 * Takes care of data processing
 * The output of the algorithm is the percentage of k-nearest neighbors to be found in a Delunay tetrahedronization
 * 
 * @author kacerekz
 */
public class CoreProcessing {

	/**
	 * Finds nearest neighbors
	 * @param data
	 */
	public static void getNearestNeighbors(int n, VertexExtended[] vertices, Edge[] edges) {
		
		// Create neighbor arrays
		for (int i = 0; i < vertices.length; i++) {
			vertices[i].nbr = new Vertex[n];
			vertices[i].nbrDist = new double[n];
		}
		
		// Iterate over edges
		// Find nearest neighbors
		// Push them into the NN array
		
		for (int i = 0; i < edges.length; i++) {
			Edge e = edges[i];
			checkNearest(e, e.v1.id, vertices);
			checkNearest(e, e.v2.id, vertices);
		}
	}

	private static void checkNearest(Edge e, int id, VertexExtended[] vertices) {
		// TODO Auto-generated method stub
		
	}
	
}
