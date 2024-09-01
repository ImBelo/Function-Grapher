package view;

import java.awt.event.KeyListener;



public interface Field {
	public String getText();
	public String getFunction();
	public void addKeyListener(KeyListener keyListener);
}
