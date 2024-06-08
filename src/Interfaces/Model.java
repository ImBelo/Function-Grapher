package Interfaces;

import java.util.List;

import Prova.ExpressionImpl;
import Prova.Graph;

public interface Model {
	
	public Camera getCamera();
	public CartesianPlane getCartesianPlane();
	public List<Graph> getGraphs();
	public void createGraph(int index, Expression expression);
	public void updateGraph(int index);

}
