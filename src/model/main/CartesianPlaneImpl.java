package model.main;
import model.factories.GraphFactoryImpl;
import model.interfaces.*;
import java.util.ArrayList;
import java.util.List;

import controller.ControllerImpl;
import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.interfaces.Expression;
import model.interfaces.Graph;


public class CartesianPlaneImpl implements CartesianPlane{
	private static CartesianPlane PLANEINSTANCE;
	private List<Graph> graphs;
	private Camera camera= CameraImpl.getInstance();
	private CartesianPlaneImpl() {
		graphs = new ArrayList<>();
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
	public void updateGraph(int i,String interval) {
		if(graphs!=null){
			Graph graph = graphs.get(i);
			// usually they are null after creating the textbox
			if(graph == null) createGraph(i,new ExpressionImpl(""));
			graph.updateGraph(interval, camera);
		}
		
	}
	public void updateGraphs() {
		if(graphs !=null && graphs.size()!=0) {
			for (int i=0;i<graphs.size();i++)
			{
				Graph graph = graphs.get(i);
				// usually they are null after creating the textbox
				if(graph == null) 
					createGraph(i,new ExpressionImpl(""));
				graph.updateGraph(camera);
			}
		
	}
	}
	@Override
	public void createGraph(int i,Expression expr) {
		// we use set instead of add because if we add in the middle of the list all the index gets shifted
		if(graphs.size()==i) {
			graphs.add(null);
		}
		
		GraphFactory gf = new GraphFactoryImpl();
		if(gf.createGraph(expr).isPresent());
			graphs.set(i,gf.createGraph(expr).get());
	}
	@Override
	public Camera getCamera() {
		return CameraImpl.getInstance();
	}



}
	
	
	
	
		
	
	
	


