package controller;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.interfaces.Model;
import model.main.Graph;
import model.main.ModelImpl;
import view.View;


public interface Controller {
	public static List<Controller> controllers= new ArrayList<>();
	public Camera getCamera();
	public Model getModel();
	// every sub controller should call this method in a static block and load them self
	public static void  addController(Controller controller) {
		controllers.add(controller);
	}
	public CartesianPlane getCartesianPlane(); 
	public void setView(View view);
	public View getView();
	public void setModel(Model model);
	public List<Graph> getGraphs();
	public void update();
	
}
