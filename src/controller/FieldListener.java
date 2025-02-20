package controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import model.interfaces.Model;
import model.main.ExpressionImpl;
import model.token.NotWellFormedFormulaException;
import view.ErrorField;
import view.Field;
import view.FunctionField;
import view.LinkedField;
import view.MyWindow;
import view.Window;

public class FieldListener implements SubController,KeyListener{
	private static FieldListener INSTANCE = null;
	private Controller controller;
	
	public FieldListener(Controller controller) {
		this.controller = controller;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		FunctionField field = (FunctionField) e.getSource();
		MyWindow window = (MyWindow)controller.getView().getWindow();
		String function = field.getText();
		String interval = field.getLinkedField().getText();
		ErrorField errorfield = controller.getView().getErrorField();
		if(function.equals(""))
			function = "0";
		Model model = controller.getModel();
		int index = field.getIndex();
		try {
			//insert graph object
			model.createGraph(index,new ExpressionImpl(function));
			//update the graph based on the state of the camera
			
			model.updateGraph(index,interval);
			errorfield.setText("");
			field.setBackground(Color.WHITE);
			window.updateUI();
		}catch(Exception ex) {
			// if there is syntax/semantic error
			errorfield.setText(ex.getMessage());
			field.setBackground(Color.RED);
		}	
		
	}

	@Override
	public void update() {
		List<LinkedField<Field,Field>> fFields =  controller.getView().getFunctionField();
		Field field = fFields.get(fFields.size()-1).getFirst();
		field.addKeyListener(this);
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}

	

	
}
