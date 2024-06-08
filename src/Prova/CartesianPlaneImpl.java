package Prova;

import java.util.ArrayList;
import java.util.List;

import Interfaces.Camera;
import Interfaces.CartesianPlane;
import Interfaces.Expression;
import Parser.AbstractCartesianPlane;
import Token.NotWellFormedFormulaException;
import factories.GraphFactory;


public class CartesianPlaneImpl extends AbstractCartesianPlane implements CartesianPlane{
	private static CartesianPlane PLANEINSTANCE;
	private static Camera camera;
	private ArrayList<Graph> graphs;
	private CartesianPlaneImpl() {
		camera = CameraImpl.getInstance();
		graphs = new ArrayList<Graph>();	
		graphs.add(null);
	};
	public static CartesianPlane getInstance() {
		if (PLANEINSTANCE == null) {
			PLANEINSTANCE = new CartesianPlaneImpl();
		}
		return PLANEINSTANCE;
	} 
	public List<Graph> getGraph() {
		return this.graphs;
	}
	public void updateGraph(int i) {
		if(graphs !=null && graphs.size() != 0) {
			Graph graph = graphs.get(i);
			try {
				graph.updateGraph();
			}catch(NotWellFormedFormulaException e) {
					
			}
		}
	}
	public void updateGraphs() {
		if(graphs !=null && graphs.size()!=0) {
			for (int i=0;i<graphs.size();i++)
			{
				Graph graph = graphs.get(i);
				try {
					graph.updateGraph();
				}catch(Exception ignored) {
					
				}
			}
		}
	}
	@Override
	public void createGraph(int i,Expression expr) {
			graphs.set(i,GraphFactory.createGraph(expr));
	}
	public double getGraphXTransl() {
		return camera.getCameraX();
	}
	public Camera getCamera() {
		return camera;
	}
	
		
	
	
	

}
