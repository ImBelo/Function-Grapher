package model.interfaces;

import java.util.List;


public interface Graph {
	static final int HEIGHT = 600;
	static final int WIDTH = 1024;
	public Point getPoint(int index);	
	public Point getNextPoint(Point p);  
	public List<Point> getPoints();
	public Expression getExpression();
	public void setExpression(Expression expr);
	public void updateGraph(String interval, Camera camera);
	public void updateGraph(Camera camera);
}
