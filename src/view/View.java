package view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class View {
	private InputPanel textsInput;
	private MyWindow window;
	private final int FRAMEX = 1300;
	private final int FRAMEY = 650;
	private final int INPUTPANELX;
	private final int INPUTPANELY;
	public View() {
		JFrame frame = new JFrame("Function Grapher");
		this.window = new MyWindow();
		this.textsInput = new InputPanel();
		INPUTPANELX = textsInput.getWidth();
		INPUTPANELY = textsInput.getHeight();
		frame.setResizable(false);
		frame.setSize(FRAMEX,FRAMEY);
		window.setBounds(INPUTPANELX,0,FRAMEX-INPUTPANELX,FRAMEY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.add(window);
		frame.add(textsInput);
		
		new Thread(window).start();															

		
	}
	
	public MyWindow getWindow() {
		return this.window; 
	}
	public InputPanel getInputPanel() {
		return this.textsInput;
	}
	public List<FunctionField> getFunctionField() {
		return this.textsInput.getTextBox();
	}
	public JButton getAddButton() {
		return this.textsInput.getAddButton();
	}
	public JButton getRemoveButton() {
		return this.textsInput.getRemoveButton();
	}

}

