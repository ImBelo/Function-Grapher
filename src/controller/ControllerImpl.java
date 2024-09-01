package controller;


import java.util.ArrayList;

import java.util.List;





public class ControllerImpl extends AbstractController implements Controller{
	private static Controller controller = null;
	// list of all the subcontrollers
	public static List<Controller> controllers = new ArrayList<Controller>();
	private ControllerImpl() {
		// load all the sub controller in th JVM to run the static block
		FieldController.load();
		WindowController.load();
		AddButtonController.load();
		RemoveButtonController.load();	 
	}
	
	//method that every subController should call statically
    public static void addController(Controller controller) {
        controllers.add(controller);
    }
	//singleton
	public static Controller getInstance() {
		if(controller == null)
			controller = new ControllerImpl();
		return controller;
	}
	
	public List<Controller> getControllers(){
		return controllers;
	}
	public void update() {
		for (Controller controller: controllers) {
			controller.update();
		}
	}
	
	
	
}
