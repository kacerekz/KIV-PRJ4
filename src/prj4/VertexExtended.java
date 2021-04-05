package prj4;

import java.awt.Color;

/**
 * Represents a vertex with attached data and manipulation methods
 * 
 * @author kacerekz
 */
public class VertexExtended implements Comparable<VertexExtended> {

	/** Vertex data */
	public Vertex v;
	
	/** Neighbor data */
	public Vertex[] nbr;
	
	/** Neighbor distance data */
	public double[] nbrDist;
	
	/** Vertex color */
	public Color color;
	
	/** Static sine table */
	public static double[] sinTable = new double[360];

	/** Static cosine table */
	public static double[] cosTable = new double[360];
	
	/** Static init */
	static {
		for (int i = 0; i < 360; i++) {
			sinTable[i] = Math.sin(Math.toRadians(i));
			cosTable[i] = Math.cos(Math.toRadians(i));
		}
	}

	/**
	 * Compares two extended vertices
	 */
	@Override
	public int compareTo(VertexExtended o) {
		return o.color.getRed() - this.color.getRed();
	}

	/**
	 * Applies pitch to vertex
	 * @param rx
	 */
	public void pitch(double rx) {
		int id = (int) (360 + rx) % 360;
		double sin = sinTable[id];
		double cos = cosTable[id];
		
		double nrx = v.x * cos + v.z * sin;
		double nrz = v.x * -sin + v.z * cos;
	
		v.x = nrx;
		v.z = nrz;
	}

	/**
	 * Applies roll to vertex
	 * @param ry
	 */
	public void roll(double ry) {
		int idiffy = (int) (360 + ry) % 360;
		double sin = sinTable[idiffy];
		double cos = cosTable[idiffy];
		
		double nry = v.y * cos + v.z * -sin;
		double nrz = v.y * sin + v.z * cos;
	
		v.y = nry;
		v.z = nrz;
	}
	
}
