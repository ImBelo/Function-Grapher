package model.main;
import java.util.LinkedList;

import model.interfaces.Camera;
import model.interfaces.Expression;
public class GraphSingleVar extends AbstractGraph{
	public GraphSingleVar(Expression expr) {
		super(expr);	
	}
	@Override
	public void updateGraph(){
		Camera camera = CameraImpl.getInstance();
		double width = camera.getCameraWidth();
		double cameraX = camera.getCameraX();
		points = new LinkedList<PointImpl>();
		// interval bounds
		double right = width/2;
		double left = -right;
		// creation of interval
		Interval interval = new Interval(left,right);
		// traslation of the interval
		interval.shift(cameraX);
		// discretization of the interval
		double dx = width/WIDTH;
		// loops all x cartesian coordinate
		if(function == null)
			return;
		for (double x = interval.getLeft(); x < interval.getRight(); x+=dx) {
			double y = Double.NaN;
			// y of the cartesian plane
			y = function.evaluateAt(x);
			this.points.add(new PointImpl(x,y));
		}
	}
	@Override
	public PointImpl getPoint(int index) {
		return points != null?points.get(index):null;
	}
	@Override
	public PointImpl getNextPoint(PointImpl p) {
		boolean exist = points.indexOf(p)+1<points.size();
		PointImpl pNext=null;
		if (exist)
			pNext = getPoint(points.indexOf(p)+1);
		return exist?pNext:p;
	}

	

}

