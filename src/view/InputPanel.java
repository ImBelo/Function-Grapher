package view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import model.interfaces.Expression;
import model.main.ExpressionImpl;

public class InputPanel extends JPanel implements Panel{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 276;
	private static final int HEIGHT = 600;
	public static final int TEXTHEIGHT = 50;
	private Button plus;
	private Button minus;
	private List<Field> textBox;

	
	public InputPanel(){
		this.setSize(WIDTH, HEIGHT);
		this.setLayout(null);
		textBox = new ArrayList<Field>();
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
	public void addText(Field t) {
		FunctionField.next();
		textBox.add(t);
		this.add((FunctionField) t);
	}

	public void removeText() {
		FunctionField.prev();
		this.remove((Component) textBox.get(textBox.size()-1));
		textBox.remove(textBox.size()-1);
	}

	public List<Expression> getFunctions() {
		return textBox.stream().map(a->(Expression)new ExpressionImpl(a.getFunction())).toList();
	}
	
	
		
	
	private void createPlusButton() {
		plus = new MyButton();
		plus.setText("+");
		plus.setBounds(WIDTH-TEXTHEIGHT, 0 , TEXTHEIGHT, TEXTHEIGHT);
		plus.setSize(TEXTHEIGHT, TEXTHEIGHT);
		this.add((JButton) plus);
		
	}
	private void createMinusButton() {
		minus = new MyButton();
		minus.setText("-");
		minus.setBounds(WIDTH-TEXTHEIGHT,-TEXTHEIGHT , TEXTHEIGHT, TEXTHEIGHT);
		minus.setSize(TEXTHEIGHT, TEXTHEIGHT);
		this.add((JButton) minus);
		
	}
	public List<Field> getTextBox() {	
		return this.textBox;
	}

	public Button getAddButton() {
		return this.plus;	
	}
	public Button getRemoveButton() {
		return this.minus;	
	}
	
}
