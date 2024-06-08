package Prova;

public class Interval {
	private double left;
	private double right;
	public Interval(double left, double right) { 
		if(left<right) {
		this.left=left;
		this.right=right;
		}
		else {
			double tmp = this.left;
			this.left = this.right;
			this.right = tmp;
		}
	}
	public double getLeft() {
		return left;
	}
	public void setLeft(double sx) {
		this.left = sx;
	}
	public double getRight() {
		return right;
	}
	public void setRight(double dx) {
		this.right = dx;
	}
	public void shift(double n) {
		this.left+=n;
		this.right+=n;
	}

}
