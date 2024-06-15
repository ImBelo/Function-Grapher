package model.main;

import model.interfaces.Camera;

// contains the data of a subset of the carteesian plane
public class CameraImpl implements Camera{
	// singleton
	private static Camera INSTANCE;
	private double CameraX;  // x cartesian coordinate of the camera
	private double CameraY; // y cartesian coordinate of the camera
	private double CameraWidth; // width of camera in carteesian coordinate
	private double CameraHeight; // height of camera in carteesian coordinate
	private static final int WIDTH = 1024; //pixels
	private static final int HEIGHT = 600; //pixels
	
	private CameraImpl() {
		this.CameraX=0.0;
		this.CameraY=0.0;
		this.CameraWidth=2.0*Graph.WIDTH/Graph.HEIGHT;
		this.CameraHeight=2.0;
	}
	public static Camera getInstance() {
		if(INSTANCE == null)
			INSTANCE = new CameraImpl();
		return INSTANCE;
	}
	public double getCameraX() {
		return CameraX;
	}
	public void setCameraX(double cameraX) {
		CameraX = cameraX;
	}
	public double getCameraY() {
		return CameraY;
	}
	public void setCameraY(double cameraY) {
		CameraY = cameraY;
	}
	public double getCameraWidth() {
		return CameraWidth;
	}
	public void setCameraWidth(double cameraWidth) {
		CameraWidth = cameraWidth;
	}
	public double getCameraHeight() {
		return CameraHeight;
	}
	public void setCameraHeight(double cameraHeight) {
		CameraHeight = cameraHeight; 
	}
	private double bottom() { // the smallest coordinate y visible in the graph 
		return CameraY - halfGraphHeight();
	}
	
	private double right() { // the biggest coordinate x visible in the graph
		return CameraX - halfGraphWidth();
	}
	
	public double toRealX(int screenX) { // trasforms x screen coordinate to x cartesian coordinate 
		return screenX / (double)WIDTH * CameraWidth + right();
	}
	
	public double toRealY(int screenY) { // trasforms y screen coordinate to y cartesian coordinate
		return (HEIGHT - screenY) / (double)HEIGHT * CameraHeight + bottom();
	}
	
	public int toScreenX(double realX) { // trasforms x cartesian coordinate to y screen coordinate
		return (int) ((realX - right()) / CameraWidth * WIDTH);
	}
	
	public int toScreenY(double realY) { // trasforms y cartesian coordinate to y screen coordinate
		return HEIGHT - (int) ((realY - bottom()) / CameraHeight * HEIGHT);
	}
	
	public double halfGraphWidth() {
		return CameraWidth / 2.0;
	}
	
	public double halfGraphHeight() { 
		return CameraHeight / 2.0;
	}
	public int getHeightPixels() {
		return HEIGHT;
	}
	public int getWidthPixels() {
		return WIDTH;
	}
}
