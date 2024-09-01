package model.main;

import controller.Controller;
import controller.ControllerImpl;
import controller.WindowController;
import view.View;
import view.ViewImpl;

import java.util.Set;

import controller.*;
public class FunctionGrapher{
	public static void main(String[] args) {
		ModelImpl model = new ModelImpl();
		View view = new ViewImpl();
		Controller controller = ControllerImpl.getInstance();
		controller.setView(view);
		controller.setModel(model);
		// sets up all the controllers
		controller.update();
		
		
	}
}