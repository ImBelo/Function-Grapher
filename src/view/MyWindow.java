package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import controller.Controller;
import controller.ControllerImpl;
import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.interfaces.Model;
import model.main.CameraImpl;
import model.main.CartesianPlaneImpl;
import model.token.NotWellFormedFormulaException;

public class MyWindow extends JPanel implements  Window{
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 2000;
	private final int HEIGHT = 1000;
	private Image image;     
	private Controller controller;

	public MyWindow() { // constructor
		setFocusable(true);
		requestFocusInWindow();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
	//	setMinimumSize(new Dimension(WIDTH, HEIGHT));
	//	setMaximumSize(new Dimension(WIDTH, HEIGHT));
	}
	
	@Override
	protected void paintComponent(Graphics g) { // this method is called every event that changes the window	
		super.paintComponent(g);	
		GraphDrawer gd = new GraphDrawerImpl(controller.getCamera(),controller.getCartesianPlane(),getWidth(),getHeight());
		image = gd.draw();
		g.drawImage(image, 0, 0, null);	
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	public int getWidth() {
		return WIDTH;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}


	



	


	
	
}