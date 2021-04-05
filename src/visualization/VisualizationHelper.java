package visualization;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;

import javax.swing.JPanel;

import prj4.Vertex;
import prj4.VertexExtended;

/**
 * Helps with drawing the visualization
 * Drawing code separated from frame maintenance code
 * 
 * @author kacerekz
 */
public class VisualizationHelper {

	/** Size of border around the visualization */
	private static final int BORDER_SIZE= 20;

	/** Size of border times two */
	private static final int BORDER_SIZE_TIMES_TWO = BORDER_SIZE * 2;

	/** Target panel */
	private VisualizationPanel panel;

	/** Data of the visualization */
	private VisualizationData data;

	/** Last zoom */
	private double lastZoom;
	
	/** Max data span in x or y axis */
	private double maxSpan;
	
	/** Last panel width */
	private int lastWidth;
	
	/** Last panel height */
	private int lastHeight;
	
	/**
	 * Creates a new VisualizationHelper
	 * @param panel Panel to draw to
	 * @param data Data to draw
	 */
	public VisualizationHelper(VisualizationPanel panel, VisualizationData data) {
		this.panel = panel;
		this.data = data;
		center(data.vertexData);
	}

	/**
	 * Paints to target panel using its drawing context
	 * @param g2 Drawing context
	 */
	public void paint(Graphics2D g2) {
		if (!isLargeEnough(panel)) return;
		transform(g2, panel, data);
		drawCenter(g2);

		map(data, panel);			
		colorize(data.vertexData);
		drawVertices(g2, data.vertexData);
	}

	/**
	 * Center vertices around the origin
	 * @param vertexData Vertices as an array
	 * @return Centered vertices
	 */
	private void center(VertexExtended[] vertexData) {
		double minX = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;

		double minY = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;

		double minZ = Double.MAX_VALUE;
		double maxZ = Double.MIN_VALUE;

		for (VertexExtended vx: vertexData) {
			Vertex v = vx.v;
			
			if (v.x < minX) {
				minX = v.x;
			} else if (v.x > maxX) {
				maxX = v.x;
			}

			if (v.y < minY) {
				minY = v.y;
			} else if (v.y > maxY) {
				maxY = v.y;
			}

			if (v.z < minZ) {
				minZ = v.z;
			} else if (v.z > maxZ) {
				maxZ = v.z;
			}
		}

		double shiftX = minX + 0.5 * (maxX - minX);
		double shiftY = minY + 0.5 * (maxY - minY);
		double shiftZ = minZ + 0.5 * (maxZ - minZ);


		for (int i = 0; i < vertexData.length; i++) {
			Vertex v = vertexData[i].v;
			v.x -= shiftX;
			v.y -= shiftY; 
			v.z -= shiftZ;
		}
	}

	/**
	 * Checks if the panel is large enough to be drawn to
	 * 
	 * @param panel Target panel
	 * @return True if panel is large enought, false otherwise
	 */
	private boolean isLargeEnough(JPanel panel) {
		return panel.getWidth() > BORDER_SIZE_TIMES_TWO && panel.getHeight() > BORDER_SIZE_TIMES_TWO;
	}

	/**
	 * Transforms the context
	 *  - Moves origin to frame center
	 *  - Flips the y axis
	 * 
	 * @param g2 Drawing context
	 * @param panel Target panel
	 * @param data Visualization data
	 */
	private void transform(Graphics2D g2, JPanel panel, VisualizationData data) {
		g2.translate(panel.getWidth() / 2 +  data.panX, panel.getHeight() / 2 + data.panY);
		g2.scale(1, -1);
	}

	/**
	 * Draws the coordinate system origin
	 * @param g2 Drawing context
	 */
	private void drawCenter(Graphics2D g2) {
		Color oldColor = g2.getColor();
		g2.setColor(Color.RED);
		g2.drawRect(-2, -2, 4, 4);
		g2.setColor(oldColor);
	}

	/**
	 * Assigns color to each vertex based on its depth
	 * @param vertexData Vertex data
	 */
	private void colorize(VertexExtended[] vertexData) {
		double minZ = Double.MAX_VALUE;
		double maxZ = Double.MIN_VALUE;

		for (VertexExtended ext: vertexData) {
			if (ext.v.z < minZ) {
				minZ = ext.v.z;
			} else if (ext.v.z > maxZ) {
				maxZ = ext.v.z;
			}
		}

		double span = maxZ - minZ;
		float intensity = 0;

		for (VertexExtended ext: vertexData) {
			intensity = (float) ((ext.v.z - minZ) / span);
			intensity = Math.max(0, intensity);
			intensity = Math.min(intensity, 1);
			ext.color = new Color(intensity, intensity, intensity);
		}
	}

	/**
	 * Maps vertices into the frame
	 * @param vertexData Vertex data
	 * @param panel Target panel
	 * @param data Visualization data
	 */
	public void map(VisualizationData data, VisualizationPanel panel) {
		
		boolean sameSize = panel.getWidth() == lastWidth && panel.getHeight() == lastHeight;
		boolean sameZoom = lastZoom == data.zoom;
		
		if (sameSize && sameZoom)
			return;
			
		if (!sameSize) {
			lastHeight = panel.getHeight();
			lastWidth = panel.getWidth();
			data.zoom = 1;
			lastZoom = 1;
		}
		
		double minX = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;

		double minY = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;

		for (VertexExtended ext: data.vertexData) {
			if (ext.v.x < minX) {
				minX = ext.v.x;
			} else if (ext.v.x > maxX) {
				maxX = ext.v.x;
			}

			if (ext.v.y < minY) {
				minY = ext.v.y;
			} else if (ext.v.y > maxY) {
				maxY = ext.v.y;
			}
		}

		maxSpan = Math.max(maxX - minX, maxY - minY);
		
		double scale = Math.min(
				panel.getHeight() - BORDER_SIZE_TIMES_TWO, 
				panel.getWidth()  - BORDER_SIZE_TIMES_TWO);

		double d = (1 / maxSpan * data.zoom) * scale;

		for (VertexExtended ext: data.vertexData) {
			ext.v.x *= d;
			ext.v.y *= d;
			ext.v.z *= d;
		}
	}
	
	/**
	 * Draws vertices using given context
	 * @param g2 Drawing context
 	 * @param vertexData Vertex data
	 */
	private void drawVertices(Graphics2D g2, VertexExtended[] vertexData) {		
		Color oldColor = g2.getColor();

		Arrays.sort(vertexData);

		for (VertexExtended ext: vertexData) {
			g2.setColor(ext.color);
			g2.fillRect((int)(ext.v.x - 2), (int)(ext.v.y - 2), 4, 4);
		}

		g2.setColor(oldColor);
	}

	/**
	 * Rotate vertices around center
	 * @param rx X axis drag
	 * @param ry Y axis drag
	 */
	public void rotate(double rx, double ry) {
		for (VertexExtended ext: data.vertexData) {
			ext.pitch(rx);
			ext.roll(ry);
		}
	}

}
