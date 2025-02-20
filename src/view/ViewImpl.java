package view;



import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.Controller;

public class ViewImpl implements View{
	private Controller myController;
	private Panel textsInput;
	private Window window;
	private ErrorField errorfield;
	private final int FRAMEX = 1350;
	private final int FRAMEY = 650;
	private int INPUTPANELX;
	private int INPUTPANELY;
	public ViewImpl() {
		BorderLayout border = new BorderLayout();
		this.window = new MyWindow(); 
	    this.textsInput = new InputPanel(border);
	    errorfield = new ErrorField();
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Function Grapher");

			INPUTPANELX = textsInput.getWidth();
			INPUTPANELY = textsInput.getHeight();
			frame.setResizable(false);
			frame.setSize(FRAMEX,FRAMEY);
			window.setBounds(INPUTPANELX,0,FRAMEX-INPUTPANELX,FRAMEY);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setLayout(null);
			frame.add((MyWindow) window,BorderLayout.EAST);
			frame.add((InputPanel) textsInput,BorderLayout.WEST);
			frame.add(errorfield,BorderLayout.WEST);
		});															

		
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
	public List<LinkedField<Field,Field>> getFunctionField() {
		return this.textsInput.getTextBox();
	}
	@Override
	public Button getAddButton() {
		return this.textsInput.getAddButton();
	}
	public Button getRemoveButton() {
		return this.textsInput.getRemoveButton();
	}
	public void setController(Controller controller) {
		this.myController = controller;
		((Window) this.window).setController(controller);
		((Panel) this.textsInput).setController(controller);
	}
	public Controller getController() {
		return this.myController;
	}
	@Override
	public ErrorField getErrorField() {
		return this.errorfield;
	}

}

