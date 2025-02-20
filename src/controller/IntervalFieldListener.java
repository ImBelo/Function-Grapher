package controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import model.interfaces.Model;
import model.main.ExpressionImpl;
import model.token.NotWellFormedFormulaException;
import view.Field;
import view.FunctionField;
import view.IntervalField;
import view.LinkedField;
import view.MyWindow;

public class IntervalFieldListener implements SubController,KeyListener{
	private static IntervalFieldListener INSTANCE = null;
	private Controller controller;
	public IntervalFieldListener(Controller controller) {
		this.controller = controller;
	}
	@Override
	public void update() {
		List<LinkedField<Field,Field>> fFields =  controller.getView().getFunctionField();
		for(var fields: fFields) {
			fields.getSecond().addKeyListener(this);
		}
	
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		IntervalField field = (IntervalField) e.getSource();
		MyWindow window = (MyWindow)controller.getView().getWindow();
		String intervalString = field.getText();
		int index = field.getIndex();
		try {
			Model model = controller.getModel();
			//update the graph based on the state of the camera
			model.updateGraph(index,intervalString);
			field.setBackground(Color.WHITE);
			window.updateUI();
		}catch(Exception ex) {
			// if there is syntax/semantic error 
			field.setBackground(Color.RED);
		}	
		
	}

	public static IntervalFieldListener getInstance(Controller controller) {
		if(INSTANCE == null)
			INSTANCE = new IntervalFieldListener(controller);
		return INSTANCE;
	}

}
