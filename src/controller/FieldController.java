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

public class FieldController extends AbstractController implements Controller,KeyListener {
	private static FieldController INSTANCE = null;

	static {
		ControllerImpl.addController(FieldController.getInstance());
	}
	private FieldController() {
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static FieldController getInstance() {
		if(INSTANCE == null)
			INSTANCE  = new FieldController();
		return INSTANCE;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		FunctionField field = (FunctionField) e.getSource();
		String function = field.getText();
		if(function.equals(""))
			function = "0";
		Model model = getModel();
		int index = field.getIndex();
		try {
			//insert graph object
			model.createGraph(index,new ExpressionImpl(function));
			//update the graph based on the state of the camera
			model.updateGraph(index);
			field.setBackground(Color.WHITE);
		}catch(NotWellFormedFormulaException ex) {
			// if there is syntax/semantic error 
			field.setBackground(Color.RED);
		}	
		
	}

	@Override
	public void update() {
		List<Field> fFields =  getView().getFunctionField();
		Field field = fFields.get(fFields.size()-1);
		field.addKeyListener(new FieldController());

		
	}
	public static void load() {
	}
	
}
