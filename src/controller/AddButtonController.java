package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import view.Button;
import view.Field;
import view.FunctionField;
import view.Panel;

public class AddButtonController extends AbstractController implements Controller,ActionListener{
	static AddButtonController INSTANCE = null;
	static {
		ControllerImpl.addController(getInstance());
	}
	private AddButtonController() {
		
	}
	public static AddButtonController getInstance() {
		if(INSTANCE == null)
			INSTANCE =  new AddButtonController();
		return INSTANCE;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		getGraphs().add(null);
		Panel panel =  getView().getInputPanel();
    	// update list
		FunctionField newField = new FunctionField();
		newField.addKeyListener(FieldController.getInstance());
		panel.addText(newField);
    	// places buttons
		panel.updateButtons();
		
	}

	@Override
	public void update() {
		Button add = getView().getAddButton();
		Panel panel = getView().getInputPanel();
		add.addActionListener(AddButtonController.getInstance());
		panel.updateButtons();

		
	}
	public static void load() {
	}


		
}
