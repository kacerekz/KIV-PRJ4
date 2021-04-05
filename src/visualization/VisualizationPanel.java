package visualization;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Takes care of drawing the visualization based on VisualizationData
 * 
 * @author kacerekz
 */
public class VisualizationPanel extends JPanel {

	/** Default ID */
	private static final long serialVersionUID = 1L;
	
	/** Data of the visualization */
	private VisualizationData data;
	
	/** External drawing helper for better decomposition */
	private VisualizationHelper helper;
	
	/** Indicated whether mouse has been dragged in last frame */
	private boolean wasDragged;
	
	/**
	 * Creates a visualization of given data
	 * @param data Data to visualize
	 */
	public VisualizationPanel(VisualizationData data) {
		this.data = data;
		this.helper = new VisualizationHelper(this, data);
		addMouseListener(createMouseListener());
		addMouseMotionListener(createMouseMotionListener());
		addMouseWheelListener(createMouseWheelListener());
	}

	/**
	 * Paints the component
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		helper.paint(g2);
	}

	/**
	 * Creates a MouseWheelListener with a zoom action
	 * @return MouseWheelListener with zoom action
	 */
	private MouseWheelListener createMouseWheelListener() {
		return new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				double r = e.getPreciseWheelRotation();
				data.zoom += r * 0.05;
				data.zoom = Math.max(0.1, data.zoom);
				repaint();
			}
		};
	}

	/**
	 * Creates a MouseMotionListener
	 * @return MouseMotionListener
	 */
	private MouseMotionListener createMouseMotionListener() {
		return new MouseMotionListener() {
			int oldX;
			int oldY;
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if (wasDragged) {

					int diffX = oldX - e.getX();
					int diffY = oldY - e.getY();

					if (SwingUtilities.isLeftMouseButton(e)) {
						helper.rotate(diffX / data.zoom, diffY / data.zoom);

					} else if (SwingUtilities.isRightMouseButton(e)) {
						data.panX -= diffX;
						data.panY -= diffY;
					}

					repaint();

					oldX = e.getX();
					oldY = e.getY();

				} else {
					wasDragged = true;
					oldX = e.getX();
					oldY = e.getY();
				}
			}
		};
	}

	/**
	 * Creates a MouseListener
	 * @return MouseListener
	 */
	private MouseListener createMouseListener() {
		return new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				wasDragged = false;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
}
