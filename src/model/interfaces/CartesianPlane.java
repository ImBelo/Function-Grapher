package model.interfaces;

import java.util.List;

import model.main.Graph;

public interface CartesianPlane {

	CartesianPlane PLANEINSTANCE = null;
	Camera getCamera();
	void updateGraph(int i);
	List<Graph> getGraph();
	void createGraph(int i,Expression expr);
	void updateGraphs();
	
	


}
