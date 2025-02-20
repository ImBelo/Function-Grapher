package view;

import java.awt.event.KeyListener;



public interface Field {
	public String getText();
	public void addKeyListener(KeyListener keyListener);
	public void linkField(Field field);
	
}
