package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;


import view.Button;
import view.Field;
import view.FunctionField;
import view.IntervalField;
import view.LinkedField;
import view.Panel;

public class AddButtonListener implements SubController,ActionListener{
	private Controller controller;
	public AddButtonListener(Controller controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.getGraphs().add(null);
		Panel panel = controller.getView().getInputPanel();
    	// update list
		Field newField = new FunctionField();
		Field newInterval = new IntervalField();
		KeyListener fListener = new FieldListener(controller);
		KeyListener iFListener= new IntervalFieldListener(controller);
		newField.addKeyListener(fListener);
		newInterval.addKeyListener(iFListener);
		panel.addText(new LinkedField<Field,Field>(newField,newInterval));
    	// places buttons
		panel.updateButtons();
		
	}

	@Override
	public void update() {
		Button add = controller.getView().getAddButton();
		Panel panel = controller.getView().getInputPanel();
		add.addActionListener(this);
		panel.updateButtons();

		
	}
	


		
}
