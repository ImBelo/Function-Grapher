package controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JPanel;

import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.interfaces.Graph;
import model.main.CameraImpl;
import view.GraphDrawer;
import view.GraphDrawerImpl;
import view.MyWindow;
import view.Window;

public class WindowListener implements SubController,MouseWheelListener,MouseListener,MouseMotionListener{
	private Point mousePt;
	private Controller controller;
	private GraphDrawer graphDrawer;
	private Window myWindow;
	private Image image;
	private Camera camera;
	private CartesianPlane plane;
	WindowListener(Controller controller) {
		this.controller = controller;
		int width = controller.getView().getWindow().getWidth();
		int height = controller.getView().getWindow().getHeight();
		myWindow = controller.getView().getWindow();
		
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Window window = controller.getView().getWindow();
		Camera camera = controller.getCamera();
		double scale = Math.pow(1.15, e.getPreciseWheelRotation());// number between )0,+inf(
		double mxReal = camera.toRealX(e.getX());
		double myReal = camera.toRealY(e.getY());
		camera.zoom(scale,mxReal,myReal);
		((JPanel)window).updateUI();
	        
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		MyWindow window =(MyWindow) controller.getView().getWindow();
		mousePt = e.getPoint();
        window.requestFocusInWindow();
        ((JPanel)window).updateUI();
		
	}
	public void update() {
		MyWindow window = (MyWindow)controller.getView().getWindow();
		window.addMouseWheelListener(this);
		window.addMouseMotionListener(this);
		window.addMouseListener(this);
	};
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MyWindow window =(MyWindow) controller.getView().getWindow();
		int dx = e.getX() - mousePt.x;
        int dy = e.getY() - mousePt.y;
        Camera camera = CameraImpl.getInstance();
        camera.translate(dx, dy);
        mousePt = e.getPoint();
        ((JPanel)window).updateUI();
         
		
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	




	

	

}
