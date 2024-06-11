package model.parser;

import java.util.List;

import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.interfaces.Expression;
import model.main.Graph;


public abstract class AbstractCartesianPlane implements CartesianPlane{
	
	@Override
	public Camera getCamera() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createGraph(int i,Expression expr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGraph(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Graph> getGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
