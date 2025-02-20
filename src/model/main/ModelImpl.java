package model.main;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.interfaces.Expression;
import model.interfaces.Graph;
import model.interfaces.Model;
import model.parser.ParserImpl;
import view.GraphDrawer;
import view.GraphDrawerImpl;

public class ModelImpl implements Model {
	private CartesianPlane cartPlane;
	private Camera camera; 
	private List<Expression> functions;
 	
	public ModelImpl() {
		cartPlane = CartesianPlaneImpl.getInstance();
		camera = CameraImpl.getInstance();
		functions = new ArrayList<Expression>();
	}
	
	public Camera getCamera() {
		return CameraImpl.getInstance();
	}
	public CartesianPlane getCartesianPlane() {
		return this.cartPlane;
	}
	public void createGraph(int i, Expression expr) {
		cartPlane.createGraph(i,expr);
	} 
	
	@Override
	public List<Graph> getGraphs() {
		return getCartesianPlane().getGraph();
		
	}

	@Override
	public void updateGraph(int index,String interval) {
		cartPlane.updateGraph(index,interval);
		
	}



}
