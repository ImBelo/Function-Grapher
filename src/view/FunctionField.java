package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import Prova.Controller;
import Token.NotWellFormedFormulaException;

public class FunctionField extends JTextField {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 226;
	private static final int HEIGHT = 50;
	private static int xPos = 0;
	private static int yPos = 0;
	private int index = 0;
	private Controller myController = Controller.getInstance();
	private String function;
	public FunctionField() {
		super();
		index = yPos/HEIGHT;
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		this.setFont(font1); 
		this.setBounds(xPos,yPos,WIDTH,HEIGHT);
		
	} 
	public static void next(){
		yPos+=HEIGHT;
		
	}
	public static void prev(){
		yPos-=HEIGHT;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public int getIndex() {
		return index;
	}
}
