package Prova;
import java.util.LinkedList;

import Interfaces.Camera;
import Interfaces.Expression;

public class GraphTwoVar extends AbstractGraph{
	public GraphTwoVar(Expression expr) {
		super(expr);	
	}
	@Override
	public void updateGraph() {
		Camera camera = CameraImpl.getInstance();
		double cameraHeight = camera.getCameraHeight();
		double cameraWidth = camera.getCameraWidth(); 
		double cameraX = camera.getCameraX();
		double cameraY = camera.getCameraY();
		points = new LinkedList<PointImpl>();
		// bounds of interval
		double right = cameraWidth/2;
		double left = -right;
		double up = cameraHeight/2;
		double down = -up;
		// creations of interval 
		Interval intervalX = new Interval(left,right);
		Interval intervalY = new Interval(down,up);
		// traslation of the interval
		intervalX.shift(cameraX); 
		intervalY.shift(cameraY); 		
		// discretization of the Interval
		double dx = cameraWidth/WIDTH;
		double dy = cameraHeight/HEIGHT;  
		double z=Double.NaN;
		// partial derivative of a point (x,y)
		double fx = 0;  // with respect of x
		double fy = 0;  // with respect of y 
		double variablesError = 1E-4;	
		double functionError = 0; // error that a function on a point x,y could have
		double zoomFactor = cameraHeight/8;  
		// its slow possible improvement
		// to do:
		// https://www.researchgate.net/publication/220393631_An_adaptive_algorithm_for_efficient_computation_of_level_curves_of_surfaces
		if(function == null)
			return;
		for (double y = intervalY.getLeft(); y < intervalY.getRight(); y+=dy) { // cartesian plane y coordinate
			for (double x = intervalX.getLeft(); x < intervalX.getRight(); x+=dx) { // cartesian plane x coordinate				
				z = function.evaluateAt(x,y); 
				// limit definition
				fx = (function.evaluateAt(x+1E-3,y)-z)/1E-3; 
				fy = (function.evaluateAt(x,y+1E-3)-z)/1E-3;
				// https://en.wikipedia.org/wiki/Propagation_of_uncertainty 
				// Simplification paragraph
				functionError = Math.sqrt((fx*fx+fy*fy)*variablesError);
				
				//this instead of z == 0.0 cause of loss of precision
				if(Math.abs(z)<functionError*zoomFactor)
					this.points.add(new PointImpl(x,y));		
			}
		}

	}
	

	@Override
	public PointImpl getPoint(int index) {
		return points != null?points.get(index):null;
	}

	@Override
	public PointImpl getNextPoint(PointImpl p) {
		return p;
	}
	
	
	

}
