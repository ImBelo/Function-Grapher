package Prova;

import java.util.ArrayList;
import java.util.List;

import Interfaces.Camera;
import Interfaces.CartesianPlane;
import Interfaces.Expression;
import Interfaces.Model;
import Parser.ParserImpl;

public class ModelImpl implements Model {
	CartesianPlane cartPlane;
	Camera camera; 
	List<Expression> functions;
	ParserImpl parser = new ParserImpl();
 	
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
	public void updateGraph(int i) {
		cartPlane.updateGraph(i);
	}

	@Override
	public List<Graph> getGraphs() {
		return getCartesianPlane().getGraph();
		
	}



}
