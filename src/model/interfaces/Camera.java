package model.interfaces;

public interface Camera {

	double getCameraX();
	double getCameraY();
	double getCameraHeight();
	double getCameraWidth();
	void setCameraX(double x);
	void setCameraY(double y);
	void setCameraHeight(double h);
	void setCameraWidth(double w);
	public double toRealX(int screenX);
	public double toRealY(int screenY);
	public int toScreenX(double realX);
	public int toScreenY(double realY);
	public int getHeightPixels();
	public int getWidthPixels();
	

}
