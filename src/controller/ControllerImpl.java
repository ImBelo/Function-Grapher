package controller;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import controller.*;
import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.interfaces.Graph;
import model.interfaces.Model;
import model.token.NotWellFormedFormulaException;
import view.*;





public class ControllerImpl implements Controller {
	private Model model;
	private View view;
	private static Controller controller = null;	
	public List<SubController> subcontrollers = new LinkedList<>();
	private ControllerImpl(Model model, View view) {
		this.model = model;
		this.view = view;
	}
    public void addSubController(SubController subcontroller) {
    	subcontrollers.add(subcontroller);
    }
    @Override
	public void start() {
    	
    	SubController cont1 = new WindowListener(this);
    	SubController cont2 = new FieldListener(this);
    	SubController cont3 = new IntervalFieldListener(this);
    	SubController cont4 = new RemoveButtonListener(this);
    	SubController cont5 = new AddButtonListener(this);
		// add all eventListener
		controller.addSubController(cont1);
		controller.addSubController(cont2);
		controller.addSubController(cont3);
		controller.addSubController(cont4);
		controller.addSubController(cont5);	
		// sets up all the subcontrollers
		controller.update();		
	}
	//singleton
	public static Controller init(Model model, View view) {
		if(controller == null)
			controller = new ControllerImpl(model,view);
		else {
			controller.setModel(model);
			controller.setView(view);
		}
		return controller;
	}
	public static Controller getInstance() {
		if(controller == null)
			throw new IllegalStateException("Controller is not initialized");
		return controller;
	}
	
	public List<SubController> getControllers(){
		return subcontrollers;
	}
	public void update() {
		for (SubController subcontroller: subcontrollers) {
			subcontroller.update();
		}
	}
	@Override
	public void setModel(Model model) {
		this.model = model;
		
	}
	@Override
	public void setView(View view) {
		this.view = view;
		
	}
	@Override
	public Model getModel() {
		return this.model;
	}
	@Override
	public View getView() {
		return this.view;
	}

	@Override
	public Camera getCamera() {
		return model.getCamera();
	}
	@Override
	public CartesianPlane getCartesianPlane() {
		return model.getCartesianPlane();
	}
	@Override
	public List<Graph> getGraphs() {
		return model.getGraphs();
	}
	
}
