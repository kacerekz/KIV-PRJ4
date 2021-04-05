package visualization;

import java.awt.Dimension;

import prj4.Edge;
import prj4.Tetrahedron;
import prj4.VertexExtended;

public class VisualizationData {

	public String fileName;
	
	public String frameName = "NNG Visualization";
	public Dimension frameDimension = new Dimension(640, 480);
	
	public Edge[] edgeData;
	public Tetrahedron[] tetrahedronData;
	public VertexExtended[] vertexData;

	public double zoom = 1;
	public int panX = 0;
	public int panY = 0;

}
