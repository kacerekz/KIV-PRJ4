package visualization;

import javax.swing.JPanel;

/**
 * Panel for selecting visualization options
 * 
 * @author kacerekz
 */
public class OptionsPanel extends JPanel {

	/** Default ID */
	private static final long serialVersionUID = 1L;

	/** Data of the visualization */
	private VisualizationData data;
	
	/**
	 * Creates a visualization of options for given data
	 * @param data Data to visualize
	 */
	public OptionsPanel(VisualizationData data) {
		this.data = data;
	}
	
}
