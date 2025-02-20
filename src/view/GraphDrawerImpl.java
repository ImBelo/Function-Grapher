package view;

import java.awt.BasicStroke;
import java.util.List;
import controller.Controller;
import controller.ControllerImpl;
import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.interfaces.Graph;
import model.interfaces.Point;
import model.main.CameraImpl;
import model.main.PointImpl;
import model.token.NotWellFormedFormulaException;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class GraphDrawerImpl implements GraphDrawer{
	private Camera camera;
	private CartesianPlane plane;
	private Graphics2D imageDrawer;
	private BufferedImage image;
	public GraphDrawerImpl(Camera camera, CartesianPlane plane, int width, int height) {
		this.camera = camera;
		this.plane = plane;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.imageDrawer = image.createGraphics();
	}
	@Override
	public Image draw() {
		drawBackground();
		drawAxis(); // draws the Axis
		drawNumbers(); // draws the numbers on the cartesian axis
		try {
			plane.updateGraphs();
		}
		catch(NotWellFormedFormulaException ignored) {	
		}
		// draws the function
		drawFunction();
		return image;
	}
	public void drawBackground () { // draws the background
		this.imageDrawer.setColor(Color.BLACK);
		this.imageDrawer.fillRect(0, 0, camera.getWidthPixels(), camera.getHeightPixels());
	}  
	public void drawAxis() { // draws the cartesian axis
		// update of the coordinates of the y and x axis
		int xAxisY = camera.toScreenY(0.0);
		int yAxisX = camera.toScreenX(0.0);
		this.imageDrawer.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
		imageDrawer.setColor(Color.WHITE);
		imageDrawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// draws the axis
		imageDrawer.drawLine(0, xAxisY, camera.getWidthPixels(), xAxisY);
		imageDrawer.drawLine(yAxisX, 0, yAxisX, camera.getHeightPixels());
		// draws the x and y in their corresponding axis
		imageDrawer.drawString("x", 0, xAxisY - 10);
		imageDrawer.drawString("y", yAxisX + 10, imageDrawer.getFontMetrics().getHeight() - 10);
	}
	public void drawFunction() {
		// draws the functions
		imageDrawer.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
		imageDrawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		List<Graph> graphs;
		if((graphs = plane.getGraph()) == null)
			return;
		
		for(int i = 0; i < graphs.size();i++) { // iterate all the graphs	
			Graph graph = graphs.get(i);
			if(graph == null) return;
			List<Point> points = graph.getPoints();
			if(points == null) return;
			points.stream()
			.forEach(p->{						// iterate all points in each graph
				Point pNext = graph.getNextPoint(p); // in 2 variables functions pNext = p
				// screen coordinate points to draw
				int x1 = camera.toScreenX(p.getX());
				int x2 = camera.toScreenX(pNext.getX());
				int y1 = camera.toScreenY(p.getY()); 
				int y2 = camera.toScreenY(pNext.getY());  
				// scaled coordinate
		//		int scaledY1 = Math.min(Math.max(y1, -1), camera.getHeightPixels()); 
		//		int scaledY2 = Math.min(Math.max(y2, -1), camera.getHeightPixels()); 
		//		int scaledX1 = Math.min(Math.max(x1, 0), camera.getWidthPixels()); 
		//		int scaledX2 = Math.min(Math.max(x2, 0), camera.getWidthPixels()); 
				// draws line on screen
				imageDrawer.drawLine(x1, y1, x2,y2);
			});
				
		}
			
			
		
	}
	public void drawNumbers() {// draws the numbers on the cartesian axis
		//if(imageDrawer == null) return;
		// update of the screen coordinates of the y and x axis 
		int	xAxisY = camera.toScreenY(0.0); 
		int yAxisX = camera.toScreenX(0.0); 
		int xNotClose = findNotClose(); // smallest x cartesian cordinate that is not too close
		imageDrawer.setFont(new Font("courier new", Font.ROMAN_BASELINE, 25));
		
		int left = (int)Math.floor(camera.getCameraX() - camera.getCameraWidth()/2)*2;
	 	int right = (int)Math.ceil(camera.getCameraX() + camera.getCameraWidth()/2)*2;
	 	int down = (int)Math.floor(camera.getCameraY() - camera.getCameraHeight()/2)*2;
	 	int up = (int)Math.ceil(camera.getCameraY() + camera.getCameraHeight()/2)*2;
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
	private int findNotClose() {	// finds the point (x,0) which is considered not close
		int pixelsTooClose = 100; // number of pixel that is considered too close
		int xNotClose = 1; // cartesian coordinate of a point
		while(camera.toScreenX(xNotClose)-camera.toScreenX(0) < pixelsTooClose) {                 
			xNotClose++;
		}
		return xNotClose;
	}
}
