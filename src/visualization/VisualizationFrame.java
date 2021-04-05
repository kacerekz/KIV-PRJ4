package visualization;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Opens the frame in which the visualization runs
 * 
 * @author kacerekz
 */
public class VisualizationFrame extends JFrame {
	
	/**	Default ID */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a frame
	 * @param name Frame title
	 * @param size Frame size
	 */
	public VisualizationFrame(String name, Dimension size) {
		setTitle(name);                  
		setLocationRelativeTo(null);                    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(size);
	}
}
