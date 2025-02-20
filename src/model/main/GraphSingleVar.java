package model.main;
import java.util.LinkedList;
import java.util.List;

import model.interfaces.Expression;
import model.interfaces.Interval;
import model.interfaces.Parser;
import model.interfaces.Point;
public class GraphSingleVar extends AbstractGraph{
	public GraphSingleVar(Expression expr,Parser parser) {
		super(expr,parser);	
	}
	@Override
	public Point getPoint(int index) {
		return getPoints() != null?getPoints().get(index):null;
	}
	@Override
	public Point getNextPoint(Point p) {
		List<Point> points = getPoints();
		boolean notLast = points.indexOf(p)+1<points.size();
		Point pNext=null;
		if (notLast)
			pNext = getPoint(points.indexOf(p)+1);
		return notLast?pNext:p;
	}

	@Override
	public List<Point> iterateAndCheckIfPointInGraph() {
		List<Point> points = new LinkedList<>();
		Interval interval = super.getInterval()[0];
		// discretization of the interval
		double dx = CameraImpl.getInstance().getCameraWidth()/WIDTH;
		// loops all x cartesian coordinate
		if(getFunction() == null || interval == null)
			return null;	
		for (double x = interval.getLeft(); x < interval.getRight(); x+=dx) {
			double y = Double.NaN;
			// y of the cartesian plane
			y = getFunction().evaluateAt(x);
			points.add(new PointImpl(x,y));
		}
		return points;
		
	}
	
	

	

}

