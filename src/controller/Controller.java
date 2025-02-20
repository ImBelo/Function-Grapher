package controller;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Camera;
import model.interfaces.CartesianPlane;
import model.interfaces.Graph;
import model.interfaces.Model;
import model.main.ModelImpl;
import view.View;


public interface Controller {
	public void setModel(Model model);
	public void setView(View view);
	public Model getModel();
	public View getView();
	public Camera getCamera();
	public CartesianPlane getCartesianPlane(); 
	public List<Graph> getGraphs();
	public void update();
	public void addSubController(SubController subcontroller);
	public void start();

	
}
