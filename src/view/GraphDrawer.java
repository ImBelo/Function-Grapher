package view;

import java.awt.Image;
import java.awt.image.BufferedImage;

public interface GraphDrawer {
	public void drawBackground();
	public void drawAxis();
	public void drawFunction();
	public void drawNumbers();
	public Image draw();
}
