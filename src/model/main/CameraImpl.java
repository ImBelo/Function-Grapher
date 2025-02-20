package model.main;

import model.interfaces.Camera;
import model.interfaces.Graph;
import view.MyWindow;

// contains the data of a subset of the cartesian plane
public class CameraImpl implements Camera{
	// singleton
	private static Camera INSTANCE;
	private double cameraX;  // x cartesian coordinate of the camera
	private double cameraY; // y cartesian coordinate of the camera
	private double cameraWidth; // width of camera in carteesian coordinate
	private double cameraHeight; // height of camera in carteesian coordinate
	private int WIDTH = 1024; //pixels
	private int HEIGHT = 600; //pixels
	
	private CameraImpl() {
		this.cameraX=0.0;
		this.cameraY=0.0;
		this.cameraWidth=2.0*Graph.WIDTH/Graph.HEIGHT;
		this.cameraHeight=2.0;
	}
	public static Camera getInstance() {
		if(INSTANCE == null)
			INSTANCE = new CameraImpl();
		return INSTANCE;
	}
	public double getCameraX() {
		return cameraX;
	}
	public void setCameraX(double cameraX) {
		this.cameraX = cameraX;
	}
	public double getCameraY() {
		return cameraY;
	}
	public void setCameraY(double cameraY) {
		this.cameraY = cameraY;
	}
	public double getCameraWidth() {
		return cameraWidth;
	}
	public void setCameraWidth(double cameraWidth) {
		this.cameraWidth = cameraWidth;
	}
	public double getCameraHeight() {
		return cameraHeight;
	}
	public void setCameraHeight(double cameraHeight) {
		this.cameraHeight = cameraHeight; 
	}
	private double bottom() { // the smallest coordinate y visible in the graph 
		return cameraY - halfGraphHeight();
	}
	
	private double right() { // the biggest coordinate x visible in the graph
		return cameraX - halfGraphWidth();
	}
	
	public double toRealX(int screenX) { // trasforms x screen coordinate to x cartesian coordinate 
		return screenX / (double)WIDTH * cameraWidth + right();
	}
	
	public double toRealY(int screenY) { // trasforms y screen coordinate to y cartesian coordinate
		return (HEIGHT - screenY) / (double)HEIGHT * cameraHeight + bottom();
	}
	
	public int toScreenX(double realX) { // trasforms x cartesian coordinate to y screen coordinate
		return (int) ((realX - right()) / cameraWidth * WIDTH);
	}
	
	public int toScreenY(double realY) { // trasforms y cartesian coordinate to y screen coordinate
		return HEIGHT - (int) ((realY - bottom()) / cameraHeight * HEIGHT);
	}
	
	public double halfGraphWidth() {
		return cameraWidth / 2.0;
	}
	
	public double halfGraphHeight() { 
		return cameraHeight / 2.0;
	}
	public void setHeightPixels(int height) {
		this.HEIGHT = height;
	}
	public void SetWidthPixels(int width) {
		this.WIDTH = width;
	}
	public int getHeightPixels() {
		return HEIGHT;
	}
	public int getWidthPixels() {
		return WIDTH;
	}
	public void zoom(double scale,double mxReal,double myReal) {
		double scaledWidth = this.cameraWidth*scale;
		if(scaledWidth<1E7 &&
		   scaledWidth > 1E-2) {
			// zooms the camera in if scale < 1 and out if scale > 1
			this.cameraWidth = this.cameraWidth*scale; 			
			this.cameraHeight = this.cameraHeight*scale; 
			// adjust the center of the cartesian plane 
			// (zoom out center gets closer, zoom in center gets further)
			this.cameraX = mxReal+(this.cameraX-mxReal)*scale;
			this.cameraY = myReal+(this.cameraY-myReal)*scale;
		}
		
	}
	public void translate(double dx,double dy) {
		cameraX = cameraX-dx/(double)WIDTH * cameraWidth;
        cameraY = cameraY+dy/(double)HEIGHT * cameraHeight;
	}
		
	
}
