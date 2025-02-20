package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Button;
import view.MyWindow;
import view.Panel;
import view.View;

public class RemoveButtonListener implements SubController,ActionListener{
	private Controller controller;
	public RemoveButtonListener(Controller controller){
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Panel panel = controller.getView().getInputPanel();
		MyWindow window = (MyWindow) controller.getView().getWindow();
		// remove last graph
		if(controller.getGraphs().size() != 0) {
			controller.getGraphs().remove(panel.getTextBox().size()-1);
		}
    	// update list
    	panel.removeText();
		panel.updateButtons();
		window.updateUI();
		
		
	}

	
	@Override
	public void update() {
		View view = controller.getView();
		Button remove = view.getRemoveButton();
		Panel panel = view.getInputPanel();
		remove.addActionListener(this);
		
	}


}
