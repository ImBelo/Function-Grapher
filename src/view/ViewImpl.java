package view;



import java.awt.Component;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ViewImpl implements View{
	private Panel textsInput;
	private Window window;
	private final int FRAMEX = 1300;
	private final int FRAMEY = 650;
	private final int INPUTPANELX;
	private final int INPUTPANELY;
	public ViewImpl() {
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
		frame.add((MyWindow) window);
		frame.add((InputPanel) textsInput);
		
		new Thread((MyWindow)window).start();															

		
	}
	@Override
	public Window getWindow() {
		return this.window; 
	}
    @Override
	public Panel getInputPanel() {
		return this.textsInput;
	}
	@Override
	public List<Field> getFunctionField() {
		return this.textsInput.getTextBox();
	}
	@Override
	public Button getAddButton() {
		return this.textsInput.getAddButton();
	}
	public Button getRemoveButton() {
		return this.textsInput.getRemoveButton();
	}

}

