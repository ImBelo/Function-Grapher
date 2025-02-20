package model.main;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.interfaces.*;
import model.parser.LexerImpl;
import model.parser.ParserImpl;
import model.token.NotWellFormedFormulaException;

public abstract class AbstractGraph implements Graph{
	private List<Point> points;
	private Interval[] intervals;
	private Expression expr; 
	private Function function; // abstract syntax tree
	private String lastIntervalString;
	private Parser parser;
	private Camera camera;
	public AbstractGraph(Expression expr,Parser parser)  {
		this.expr = expr;
		this.parser = parser;
		this.function = parser.parse(expr);
		points = new LinkedList<Point>();
		intervals = new Interval[2];
	}
	public void setParser(Parser parser) {
		this.parser=parser;
		this.function=parser.parse(expr);
		
	}
	public void updateGraph(Camera camera) {
		this.camera = camera;
		updateGraph(lastIntervalString, camera);
	}
	@Override
	public void updateGraph(String interval, Camera camera) {
		this.camera = camera;
		lastIntervalString = interval;
		Interval[] intervals1 = IntervalFactory.createInterval(interval);
		Interval[] defaultIntervals = createDefaultInterval();
		this.intervals = defaultIntervals;
		for(int i = 0;i<intervals1.length;i++){
			if(intervals1!= null && intervals1[i] != null)
				this.intervals[i] = intervals[i].intersectWith(intervals1[i]);
		}
		this.points = iterateAndCheckIfPointInGraph();
	}
	public Interval[] createDefaultInterval() {
		Camera camera = CameraImpl.getInstance();
		double cameraHeight = camera.getCameraHeight();
		double cameraWidth = camera.getCameraWidth(); 
		double cameraX = camera.getCameraX();
		double cameraY = camera.getCameraY();
		// bounds of interval
		double right = cameraWidth/2;
		double left = -right;
		double up = cameraHeight/2;
		double down = -up;
		// creations of interval 
		Interval intervalX = new IntervalImpl(left,right);
		Interval intervalY = new IntervalImpl(down,up);
		// traslation of the interval
		intervalX.shift(cameraX); 
		intervalY.shift(cameraY); 	
		Interval[] myIntervals = new Interval[2];
		myIntervals[0] = intervalX;
		myIntervals[1] = intervalY;
		return myIntervals;
		
	}
	// method to calculate points
	public abstract List<Point> iterateAndCheckIfPointInGraph();
	public abstract Point getPoint(int index);
	public abstract Point getNextPoint(Point p);
	public void setInterval(Interval ...intervals) {
		this.intervals = intervals;
	}
	public Interval[] getInterval() {
		return this.intervals;
	}
	public void setExpression(Expression expr) {
		this.expr = expr;
		this.function = (parser.parse(expr));
	}
	public Expression getExpression() {
		return this.expr;
	}
	public List<Point> getPoints(){
		return this.points;
	}
	public void setPoints(List<Point> points){
		this.points = points;
	}
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}	
	

	

}
