package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import model.interfaces.Camera;
import view.MyWindow;
import view.Window;

public class WindowController extends AbstractController implements MouseWheelListener,MouseListener,MouseMotionListener{
	private Point mousePt;
	private static WindowController INSTANCE = null;
	static {
		ControllerImpl.addController(WindowController.getInstance());
	}
	private WindowController() {	
	}
	public static WindowController getInstance() {
		if(INSTANCE == null)
			INSTANCE = new WindowController();
		return INSTANCE;
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Window window = getView().getWindow();
		Camera camera = getCamera();
		double scale = Math.pow(1.15, e.getPreciseWheelRotation());// number between )0,+inf(
		// mouse position in cartesian coordinate
		double mxReal = camera.toRealX(e.getX()); 
		double myReal = camera.toRealY(e.getY()); 
		// zooms the camera in if scale < 1 and out if scale > 1
		camera.setCameraWidth(camera.getCameraWidth()*scale); 
		camera.setCameraHeight(camera.getCameraHeight()*scale); 
		// adjust the center of the cartesian plane 
		// (zoom out center gets closer, zoom in center gets further)
		camera.setCameraX(mxReal+(camera.getCameraX()-mxReal)*scale);
		camera.setCameraY(myReal+(camera.getCameraY()-myReal)*scale);
		((JPanel) window).updateUI();
	        
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		MyWindow window =(MyWindow) getView().getWindow();
		mousePt = e.getPoint();
        window.requestFocusInWindow();
        window.updateUI();
		
	}
	public void update() {
		MyWindow window = (MyWindow)getView().getWindow();
		WindowController windowController = WindowController.getInstance();
		window.addMouseWheelListener(windowController);
		window.addMouseMotionListener(windowController);
		window.addMouseListener(windowController);
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
		MyWindow window =(MyWindow) getView().getWindow();
		int dx = e.getX() - mousePt.x;
        int dy = e.getY() - mousePt.y;
        Camera camera = getCamera();
        camera.setCameraX(camera.getCameraX()-dx/(double)MyWindow.getWidthWindow()* camera.getCameraWidth());
        camera.setCameraY(camera.getCameraY()+dy/(double)MyWindow.getHeightWindow() * camera.getCameraHeight());
        mousePt = e.getPoint();
        window.updateUI();
         
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void load() {
		
	}




	

	

}
