package Prova;

import java.awt.BasicStroke;
import java.lang.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Interfaces.Camera;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;

public class GraphDrawer {
	public void drawBackground (Graphics2D imageDrawer) { // draws the background
		Camera camera = CameraImpl.getInstance();
		imageDrawer.setColor(Color.BLACK);
		imageDrawer.fillRect(0, 0, camera.getWidthPixels(), camera.getHeightPixels());
		
	}  
	public void drawAxis(Graphics2D imageDrawer) { // draws the cartesian axis
		Camera camera = CameraImpl.getInstance();
		// update of the coordinates of the y and x axis
		int xAxisY = camera.toScreenY(0.0);
		int yAxisX = camera.toScreenX(0.0);
		imageDrawer.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
		imageDrawer.setColor(Color.WHITE);
		imageDrawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// draws the axis
		imageDrawer.drawLine(0, xAxisY, camera.getWidthPixels(), xAxisY);
		imageDrawer.drawLine(yAxisX, 0, yAxisX, camera.getHeightPixels());
		// draws the x and y in their corresponding axis
		imageDrawer.drawString("x", 0, xAxisY - 10);
		imageDrawer.drawString("y", yAxisX + 10, imageDrawer.getFontMetrics().getHeight() - 20);
	}
	public void drawFunction(Graphics2D imageDrawer) { // draws the functions
		Camera camera = CameraImpl.getInstance();
		Controller myController = Controller.getInstance();
		imageDrawer.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
		imageDrawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//shallow copy
		List<Graph> copy = new ArrayList<>();
		copy.addAll(myController.getGraphs());
		copy.stream()
		.filter(graph->graph!=null && graph.getPoints() != null)
		.map(graph->Map.of(graph,graph.getPoints()))
		.forEach(map-> { 
			for(var entry : map.entrySet()) {
				Graph graph = entry.getKey();
				List<PointImpl> points = entry.getValue();
				
				points.stream()
				.forEach(p->{
					PointImpl pNext = graph.getNextPoint(p); 
					// screen coordinate points to draw
					int x1 = camera.toScreenX(p.getX());
					int x2 = camera.toScreenX(pNext.getX());
					int y1 = camera.toScreenY(p.getY()); 
					int y2 = camera.toScreenY(pNext.getY());  

					int scaledY1 = Math.min(Math.max(y1, 0), camera.getHeightPixels()-2); 
					int scaledY2 = Math.min(Math.max(y2, 0), camera.getHeightPixels()-2); 
					int scaledX1 = Math.min(Math.max(x1, 0), camera.getWidthPixels()-2); 
					int scaledX2 = Math.min(Math.max(x2, 0), camera.getWidthPixels()-2); 
					
					imageDrawer.drawLine(scaledX1, scaledY1, scaledX2, scaledY2);
				});
			}
		});
	}
	public void drawNumbers(Graphics2D imageDrawer) {// draws the numbers on the cartesian axis
		Camera camera = CameraImpl.getInstance();
		// update of the screen coordinates of the y and x axis 
		int	xAxisY = camera.toScreenY(0.0); 
		int yAxisX = camera.toScreenX(0.0); 
		int xNotClose = findNotClose(camera); // smallest x cartesian cordinate that is not too close
		imageDrawer.setFont(new Font("courier new", Font.ROMAN_BASELINE, 25));
		
		int left = (int)Math.floor(camera.getCameraX() - camera.getCameraWidth()/2);
	 	int right = (int)Math.ceil(camera.getCameraX() + camera.getCameraWidth()/2);
	 	int down = (int)Math.floor(camera.getCameraY() - camera.getCameraHeight()/2);
	 	int up = (int)Math.ceil(camera.getCameraY() + camera.getCameraHeight()/2);
		// y number inside camera
	 	int i = down;
	 	while(i<up) {
			if(i % xNotClose == 0) {
				// draws numbers in y axis
				imageDrawer.drawString(Integer.toString(i), yAxisX, camera.toScreenY(i)); 
				// draws the axis parallel to the x axis
				imageDrawer.setStroke(new BasicStroke(0.3f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
				imageDrawer.drawLine(0,camera.toScreenY(i), camera.getWidthPixels(), camera.toScreenY(i));// x axis 
				i+=xNotClose;
			}
			else {
				i++;
			}	 
		}
	 	// x number inside camera
	 	i = left;
		while(i<right) {
			if(i % xNotClose == 0) {	
				// draws number in x axis
				imageDrawer.drawString(Integer.toString(i), camera.toScreenX(i), xAxisY); 
				// draws the axis parallel to the y axis
				imageDrawer.setStroke(new BasicStroke(0.3f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
				imageDrawer.drawLine(camera.toScreenX(i), 0, camera.toScreenX(i), camera.getHeightPixels());  
				
				i+=xNotClose;
			}
			else {
				i++;
			}
			 
	}
		
	}
	private int findNotClose(Camera camera) {	// finds the point (x,0) which is considered not close
		int pixelsTooClose = 100; // number of pixel that is considered too close
		int xNotClose = 1; // cartesian coordinate of a point
		while(camera.toScreenX(xNotClose)-camera.toScreenX(0) < pixelsTooClose) {                 
			xNotClose++;
		}
		return xNotClose;
	}
}
