package Prova;

import java.util.List;

import Interfaces.Camera;


public interface Graph {
	static final int HEIGHT = 600;
	static final int WIDTH = 1024;
	public void updateGraph();
	public PointImpl getPoint(int index);	
	public PointImpl getNextPoint(PointImpl p);  
	public List<PointImpl> getPoints();
	public int size();
	
}
