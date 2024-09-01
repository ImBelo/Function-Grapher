package controller;

import java.util.List;

import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.interfaces.Model;
import model.main.Graph;
import view.View;

public abstract class AbstractController implements Controller{
	static private Model model;
	static private View view;
	@Override
	public void setModel(Model model) {
		AbstractController.model = model;
	}

	@Override
	public void setView(View view) {
		AbstractController.view = view;
	}
	@Override
	public Model getModel() {
		return model;
	}
	@Override
	public View getView() {
		return view;
	}
	


	@Override
	public List<Graph> getGraphs() {
		return getModel().getGraphs();
	}

	public Camera getCamera() {
		return getModel().getCamera();
	}

	
	@Override
	public CartesianPlane getCartesianPlane() {
		return getModel().getCartesianPlane();
	}

	

}
