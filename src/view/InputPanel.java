package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaces.Expression;
import Prova.Controller;
import Prova.ExpressionImpl;

public class InputPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 276;
	private static final int HEIGHT = 600;
	public static final int TEXTHEIGHT = 50;
	private JButton plus;
	private JButton minus;
	private ArrayList<FunctionField> textBox;
	private Controller myController;
	
	public InputPanel(){
		this.setSize(WIDTH, HEIGHT);
		this.setLayout(null);
		textBox = new ArrayList<FunctionField>();
		this.setBounds(0,0,WIDTH,HEIGHT);
		// first input field
		FunctionField first = new FunctionField();
		addText(first);
		createPlusButton();
		createMinusButton();
	}
	public List<String> getText() {
	  	return textBox.stream().map(f->f.getText()).toList();
	}

	public void updateButtons() {
		plus.setBounds(WIDTH-TEXTHEIGHT, TEXTHEIGHT*(textBox.size()-1), TEXTHEIGHT, TEXTHEIGHT);
		minus.setBounds((WIDTH-TEXTHEIGHT),TEXTHEIGHT*(textBox.size()-2), TEXTHEIGHT, TEXTHEIGHT);
		this.updateUI();
	}
	public void addText(FunctionField t) {
		FunctionField.next();
		textBox.add(t);
		this.add(t);
	}

	public void removeText() {
		FunctionField.prev();
		this.remove(textBox.get(textBox.size()-1));
		textBox.remove(textBox.size()-1);
	}

	public List<Expression> getFunctions() {
		return textBox.stream().map(a->(Expression)new ExpressionImpl(a.getFunction())).toList();
	}
	
	
		
	
	private void createPlusButton() {
		plus = new JButton();
		plus.setText("+");
		plus.setBounds(WIDTH-TEXTHEIGHT, 0 , TEXTHEIGHT, TEXTHEIGHT);
		plus.setSize(TEXTHEIGHT, TEXTHEIGHT);
		this.add(plus);
		
	}
	private void createMinusButton() {
		minus = new JButton();
		minus.setText("-");
		minus.setBounds(WIDTH-TEXTHEIGHT,-TEXTHEIGHT , TEXTHEIGHT, TEXTHEIGHT);
		minus.setSize(TEXTHEIGHT, TEXTHEIGHT);
		this.add(minus);
		
	}
	public List<FunctionField> getTextBox() {	
		return this.textBox;
	}

	public JButton getAddButton() {
		return this.plus;	
	}
	public JButton getRemoveButton() {
		return this.minus;	
	}
}
