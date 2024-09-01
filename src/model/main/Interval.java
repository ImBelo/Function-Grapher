package model.main;

public interface Interval {
	public double getLeft();
	public double getRight();
	public void setLeft(double left);
	public void setRight(double right);
	public void shift(double n);
	
}
