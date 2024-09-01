package model.main;

import java.util.List;

import model.interfaces.Expression;
import model.interfaces.Point;


public interface Graph {
	static final int HEIGHT = 600;
	static final int WIDTH = 1024;
	public void updateGraph();
	public Point getPoint(int index);	
	public Point getNextPoint(Point p);  
	public List<Point> getPoints();
	public Expression getExpression();
	public void setExpression(Expression expr);
}
