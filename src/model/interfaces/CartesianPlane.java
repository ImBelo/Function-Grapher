package model.interfaces;

import java.util.List;

public interface CartesianPlane {
	Camera getCamera();
	void updateGraph(int i,String interval);
	List<Graph> getGraph();
	void createGraph(int i,Expression expr);
	void updateGraphs();
	
	


}
