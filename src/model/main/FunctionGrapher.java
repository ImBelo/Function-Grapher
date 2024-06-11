package model.main;

import controller.Controller;
import view.View;

public class FunctionGrapher{
	public static void main(String[] args) {
		ModelImpl model = new ModelImpl();
		View view = new View();
		Controller controller = Controller.getInstance();
		controller.setView(view);
		controller.setModel(model);
		controller.updateView();
		
	}
}