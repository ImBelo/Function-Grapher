package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Button;
import view.Panel;

public class RemoveButtonController extends AbstractController implements Controller,ActionListener{
	private static RemoveButtonController INSTANCE = null;
	static {
		ControllerImpl.addController(getInstance());
	}
	private RemoveButtonController(){
		
	}
	public static RemoveButtonController getInstance() {
		if(INSTANCE == null)
			INSTANCE = new RemoveButtonController();
		return INSTANCE;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Panel panel = getView().getInputPanel();
		// remove last graph 
    	getGraphs().remove(panel.getTextBox().size()-1);
    	// update list
    	panel.removeText();
		panel.updateButtons();
		
	}

	
	@Override
	public void update() {
		Button remove = getView().getRemoveButton();
		Panel panel = getView().getInputPanel();
		remove.addActionListener(RemoveButtonController.getInstance());
		
	}
	public static void load() {
		
	}

}
