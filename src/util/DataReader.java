package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import prj4.Tetrahedron;
import prj4.Vertex;

/**
 * Class used to read input files
 * 
 * @author kacerekz
 */
public class DataReader {

	/**
	 * Reads vertices from an .obj file
	 * @param filename Path to file
	 * @return Vertices from the .obj as an array
	 */
	public static Vertex[] readOBJ(String filename) {
		List<Vertex> vertices = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));

			String line;
			String[] split;

			int id = 0;

			while ((line = br.readLine()) != null) {
				if (line.trim().startsWith("v")){
					split = line.trim().split(" ");
					Vertex v = new Vertex(
							Double.parseDouble(split[1]),
							Double.parseDouble(split[2]),
							Double.parseDouble(split[3]));
					v.id = id++;
					vertices.add(v);
				}
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading .obj file at " + filename + ".");
		}

		return vertices.toArray(new Vertex[vertices.size()]);
	}


	/**
	 * Reads tetrahedra from a .dat file
	 * @param filename Path to file
	 * @return Tetrahedra from the .dat as an array
	 */
	public static DATFile readDAT(String filename) {
		Vertex[] vertices;
		Tetrahedron[] tetrahedra;

		int vertexCount;
		int tetrahedronCount;

		String line;
		String[] split;

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File (filename)));

			// Read vertex coordinates
			vertexCount = Integer.parseInt(br.readLine());
			vertices = new Vertex[vertexCount];
			
			for (int i = 0; i < vertexCount; i++) {
				line = br.readLine().trim();
				split = line.split(" ");
				
				vertices[i]	= new Vertex(
						Double.parseDouble(split[0]),
						Double.parseDouble(split[1]),
						Double.parseDouble(split[2]));
				vertices[i].id = i;
			}

			// Read tetrahedra as vertex indices
			tetrahedronCount = Integer.parseInt(br.readLine());
			tetrahedra = new Tetrahedron[tetrahedronCount];
			
			for (int i = 0; i < tetrahedronCount; i++) {
				line = br.readLine().trim();
				split = line.split("\\s+");

				tetrahedra[i] = new Tetrahedron(
						vertices[Integer.parseInt(split[0])],
						vertices[Integer.parseInt(split[1])],
						vertices[Integer.parseInt(split[2])],
						vertices[Integer.parseInt(split[3])]
						);
			}

			br.close();
			return new DATFile(vertices, tetrahedra);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading .dat file at " + filename + ".");
		}

		return new DATFile(new Vertex[] {}, new Tetrahedron[] {});
	}

}
