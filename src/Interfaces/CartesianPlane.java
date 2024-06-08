package Interfaces;

import java.util.ArrayList;
import java.util.List;

import Prova.CameraImpl;
import Prova.CartesianPlaneImpl;
import Prova.ExpressionImpl;
import Prova.Graph;

public interface CartesianPlane {

	CartesianPlane PLANEINSTANCE = null;
	Camera getCamera();
	void updateGraph(int i);
	List<Graph> getGraph();
	void createGraph(int i,Expression expr);
	void updateGraphs();
	
	


}
