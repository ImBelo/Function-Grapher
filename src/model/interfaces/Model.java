package model.interfaces;

import java.util.List;

public interface Model {
	
	public Camera getCamera();
	public CartesianPlane getCartesianPlane();
	public List<Graph> getGraphs();
	public void createGraph(int index, Expression expression);
	public void updateGraph(int index,String interval);

}
