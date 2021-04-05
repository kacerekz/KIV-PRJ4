package processing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import prj4.Edge;
import prj4.Tetrahedron;
import prj4.Vertex;

/**
 * Processes edges to retrieve unique ones
 * 
 * @author kacerekz
 */
public class EdgeProcessing {

	/**
	 * Gets all edges in the model
	 * @param tetrahedra All tetrahedra
	 * @return All edges
	 */
	public static Edge[] getEdges(Tetrahedron[] tetrahedra) {
		List<Edge> edges = new ArrayList<Edge>();

		for (int i = 0; i < tetrahedra.length; i++) {

			for (int j = 0; j < 4; j++) {
				for (int k = j + 1; k < 4; k++) {

					Vertex v1 = tetrahedra[i].vertices[j];
					Vertex v2 = tetrahedra[i].vertices[k];
					if (v1.id < v2.id){
						edges.add(new Edge(v1, v2));
					} else {
						edges.add(new Edge(v2, v1));
					}

				}
			}
		}

		return edges.toArray(new Edge[edges.size()]);
	}

	/**
	 * Extracts unique edges from edges in lexicographical order
	 * @param edges Ordered edges
	 * @return Unique edges
	 */
	public static Edge[] getUniqueEdges(Edge[] edges) {
		List<Edge> unique = new ArrayList<>();
		
		Edge current = edges[0];
		unique.add(current);
		
		for (int i = 1; i < edges.length; i++) {
			 if (edges[i].compareTo(current) != 0) {
				 current = edges[i];
				 unique.add(current);		 
			 }
		}
		
		return (Edge[]) unique.toArray(new Edge[unique.size()]);
	}
	
	/**
	 * Testing unique edge extraction
	 * @param data Tetrahedron data
	 */
	public static void testCreateEdges(Tetrahedron[] data) {
		Edge[] edges = getEdges(data);
		Arrays.sort(edges);
		Edge[] unique = getUniqueEdges(edges);
		
		System.out.println("Items in edges: " + edges.length);
		System.out.println("Items in unique: " + unique.length);
		validate(edges, unique);
	}
	
	/**
	 * Validates unique edge extraction result
	 * @param edges Ordered edges
	 * @param unique Unique edges
	 */
	private static void validate(Edge[] edges, Edge[] unique) {
		ArrayList<Edge> edgeList = new ArrayList<>();
		
		for (int i = 0; i < edges.length; i++) {
			edgeList.add(edges[i]);
		}
		
		for (Edge e : unique) {
			ArrayList<Edge> tmp = new ArrayList<>();
			for (int i = 0; i < edgeList.size(); i++) {
				if (e.compareTo(edgeList.get(i)) != 0) {
					tmp.add(edgeList.get(i));
				}
			}
			edgeList = tmp;
		}
		
		System.out.println("Items remaining: " + edgeList.size());
	}
	
}
