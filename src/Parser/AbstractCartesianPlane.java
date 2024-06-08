package Parser;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import Interfaces.Camera;
import Interfaces.CartesianPlane;
import Interfaces.Expression;
import Prova.ExpressionImpl;
import Prova.Graph;


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
