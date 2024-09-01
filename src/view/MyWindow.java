package view;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import controller.Controller;
import controller.ControllerImpl;
import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.main.CameraImpl;
import model.main.CartesianPlaneImpl;
import model.token.NotWellFormedFormulaException;

public class MyWindow extends JPanel implements  Window,KeyListener, Runnable{
	private static final long serialVersionUID = 1L;
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 600;
	private BufferedImage image;   
	private Graphics2D imageDrawer;   
	private GraphDrawer graphDrawer;  
	private Point mousePt;  // posizione mouse

	
	
	public MyWindow() { // constructor 
		CartesianPlane cartPlane= CartesianPlaneImpl.getInstance();
		Camera camera = CameraImpl.getInstance();
		
		setFocusable(true);
		requestFocusInWindow();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		imageDrawer = image.createGraphics();
		graphDrawer = new GraphDrawer();
		
	
	}
	


	@Override
	protected void paintComponent(Graphics g) {
		CartesianPlane cartPlane = ControllerImpl.getInstance().getCartesianPlane();
		Camera camera = cartPlane.getCamera();
		super.paintComponent(g);
		// draws the background
		graphDrawer.drawBackground(imageDrawer);
		graphDrawer.drawAxis(imageDrawer); // draws the Axis
		graphDrawer.drawNumbers(imageDrawer); // draws the numbers on the cartesian axis
		synchronized (this) {
		
			try {
			 cartPlane.updateGraphs();
			}
			catch(NotWellFormedFormulaException e) {
				
				
			}
			 // draws the function
			graphDrawer.drawFunction(imageDrawer);
		}
		
		g.drawImage(image, 0, 0, null);
		
	}
	
	@Override
	public void run() {
		boolean running = true;
		
		while (running) {
			// while thread runs repaint
			
			repaint();
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		

			
		
			
		
	}
	public MyWindow outer() {
		   return this;
		}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	
	public static int getHeightWindow() {
		return HEIGHT;
	}
	
	public static int getWidthWindow() {
		return WIDTH;
	}

	

	public Graphics2D getImageDrawer() {
		return this.imageDrawer;
	}

	public void setImageDrawer(Graphics2D imageDrawer) {
		this.imageDrawer = imageDrawer;
	}
	public BufferedImage getBufferedImage() {
		return this.image;
	}

	public GraphDrawer getGraphDrawer() {
		return this.graphDrawer;
	}



	



	


	
	
}