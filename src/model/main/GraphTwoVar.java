package model.main;
import java.util.LinkedList;
import java.util.List;

import model.interfaces.Camera;
import model.interfaces.Expression;
import model.interfaces.Function;
import model.interfaces.Interval;
import model.interfaces.Parser;
import model.interfaces.Point;

public class GraphTwoVar extends AbstractGraph{
	public GraphTwoVar(Expression expr,Parser parser) {
		super(expr,parser);	
	}
	
	@Override
	public Point getPoint(int index) {
		return getPoints() != null?getPoints().get(index):null;
	}

	@Override
	public Point getNextPoint(Point p) {
		return p;
	}
	
	@Override
	public List<Point> iterateAndCheckIfPointInGraph() {
		List<Point> points = new LinkedList<>(); 
		// discretization of the Interval
		Camera camera = CameraImpl.getInstance();
		double cameraWidth = camera.getCameraWidth(); 
		double cameraHeight = camera.getCameraHeight();
		double dx = cameraWidth/WIDTH;
		double dy = cameraHeight/HEIGHT;  
		double z=Double.NaN;
		// partial derivative of a point (x,y)
		double fx = 0;  // with respect of x
		double fy = 0;  // with respect of y 
		double variablesError = 1E-4;	
		double functionError = 0; // error that a function on a point x,y could have
		double zoomFactor = cameraHeight/8;  
		Interval[] intervals = getInterval();
		Function f = getFunction();
		// its slow possible improvement
		// TODO:
		// https://www.researchgate.net/publication/220393631_An_adaptive_algorithm_for_efficient_computation_of_level_curves_of_surfaces
		if(f == null || intervals == null)
			return null;
		for (double y = intervals[1].getLeft(); y < intervals[1].getRight(); y+=dy) { // cartesian plane y coordinate
			for (double x = intervals[0].getLeft(); x < intervals[0].getRight(); x+=dx) { // cartesian plane x coordinate				
				z = getFunction().evaluateAt(x,y); 
				// limit definition	
				fx = (getFunction().evaluateAt(x+1E-3,y)-z)/1E-3; 
				fy = (getFunction().evaluateAt(x,y+1E-3)-z)/1E-3;
				// https://en.wikipedia.org/wiki/Propagation_of_uncertainty 
				// Simplification paragraph
				functionError = Math.sqrt((fx*fx+fy*fy)*variablesError);
						
				//this instead of z == 0.0 cause of loss of precision
				if(Math.abs(z)<functionError*zoomFactor)
					points.add(new PointImpl(x,y));		
			}
		}
		return points;
		
}
	
	
	
	

}
