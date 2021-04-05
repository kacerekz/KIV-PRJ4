package prj4;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import processing.InputProcessing;
import util.DATFile;
import util.DataReader;
import visualization.OptionsPanel;
import visualization.VisualizationData;
import visualization.VisualizationFrame;
import visualization.VisualizationPanel;

public class Main {

	public static void main(String[] args) {
		
		// Read parameters from CMD
		VisualizationData data = readParameters(args);		
		data.fileName = "data/NNG/DT500.dat";
		//data.fileName = "data/sphere2.obj";
		
		// Read input file
		if (data.fileName.endsWith(".obj")) {
			Vertex[] vertices = DataReader.readOBJ(data.fileName);
			data.vertexData = InputProcessing.createVertices(vertices);

		} else if (data.fileName.endsWith(".dat")) {
			DATFile file = DataReader.readDAT(data.fileName);
			data.vertexData = InputProcessing.createVertices(file.vertices);
			data.tetrahedronData = file.tetrahedra;
			data.edgeData = InputProcessing.createEdges(data.tetrahedronData);	
		
		} else {
			System.out.println("Unsupported file format.");
			System.exit(0);
		}
		
		// Create visualization
		JFrame frame = new VisualizationFrame(data.frameName, data.frameDimension);
		JPanel drawingPanel = new VisualizationPanel(data);
		JPanel optionsPanel = new OptionsPanel(data);
		
		frame.setLayout(new BorderLayout());
		frame.add(drawingPanel, BorderLayout.CENTER);
		frame.add(optionsPanel, BorderLayout.EAST);
		
		frame.pack();
		frame.setVisible(true);
	}

	private static VisualizationData readParameters(String[] args) {
		VisualizationData data = new VisualizationData();

		return data;
	}
	
}
