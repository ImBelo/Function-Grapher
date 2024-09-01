package model.main;

import java.util.ArrayList;
import java.util.List;

import model.factories.GraphFactory;
import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.interfaces.Expression;


public class CartesianPlaneImpl implements CartesianPlane{
	private static CartesianPlane PLANEINSTANCE;
	private List<Graph> graphs;
	private CartesianPlaneImpl() {
		graphs = new ArrayList<>();	
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
		if(graphs != null && graphs.size() != 0) {
			Graph graph = graphs.get(i);
			// usually they are null after creating the textbox
			if(graph == null) createGraph(i,new ExpressionImpl(""));
			graph.updateGraph();
			
		}
	}
	public void updateGraphs() {
		if(graphs !=null && graphs.size()!=0) {
			for (int i=0;i<graphs.size();i++)
			{
				Graph graph = graphs.get(i);
				// usually they are null after creating the textbox
				if(graph == null) createGraph(i,new ExpressionImpl(""));
				graph.updateGraph();
			}
		
	}
	}
	@Override
	public void createGraph(int i,Expression expr) {
		// we use set instead of add because if we add in the middle of the list all the index gets shifted
			if(graphs.size()==i) {
				graphs.add(null);
			}
			graphs.set(i,GraphFactory.createGraph(expr));
	}
	@Override
	public Camera getCamera() {
		return CameraImpl.getInstance();
	}
	
	
	
		
	
	
	

}
