package Prova;

import java.awt.Color;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

import Interfaces.Camera;
import Interfaces.CartesianPlane;
import Interfaces.Model;
import Token.NotWellFormedFormulaException;
import view.FunctionField;
import view.InputPanel;
import view.MyWindow;
import view.View;

public class Controller implements KeyListener,MouseWheelListener,ActionListener{
	private Model model;
	private View view;
	private static Controller controller = null;
	private Point mousePt;
	
	private Controller () {
		
	}
	public static Controller getInstance() {
		if(controller == null)
			controller = new Controller();
		return controller;
	}
	public void setModel(Model model) {
		this.model = model; 
	}
	public void setView(View view) {
		this.view = view; 
	}
	// listeners setup 
	public void updateView() {     
		updateWindow();
		updateFunctionField();
		updateButton();
   	}
	
	 // model
	 public CartesianPlane getCartesianPlane() {
		 return this.model.getCartesianPlane(); 	 
	 }
	 public List<Graph> getGraphs() {
		 return this.model.getCartesianPlane().getGraph(); 	 
	 }
	 public Camera getCamera() {
		 return this.model.getCamera();
	 }
	 
	
	public Model getModel() {
		return this.model;
	}
	// zooms window
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		 if (e.getSource() == view.getWindow()) {
			 Camera camera = Controller.getInstance().getCamera();
			double scale = Math.pow(1.15, e.getPreciseWheelRotation());// number between [0,+inf]
			// mouse position in cartesian coordinate
			double mxReal = camera.toRealX(e.getX()); 
			double myReal = camera.toRealY(e.getY()); 
			//
			double sx = (camera.getCameraX() - mxReal) / camera.getCameraWidth();
			double sy = (camera.getCameraY() - myReal) / camera.getCameraHeight();
			// zooms the graph
			camera.setCameraWidth(camera.getCameraWidth()*scale); 
			camera.setCameraHeight(camera.getCameraHeight()*scale); 
			//if zoom in
			camera.setCameraX(mxReal+sx*camera.getCameraWidth());
			camera.setCameraY(myReal+sy*camera.getCameraHeight());
			view.getWindow().updateUI();
		        
			 
		 }
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	private void updateWindow() {
		 view.getWindow().addMouseWheelListener(this);
		 // get mouse position
		 view.getWindow().addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) { // takes the potision of the mouse
	                mousePt = e.getPoint();
	                view.getWindow().requestFocusInWindow();
	                view.getWindow().updateUI();
	            }
	        });
		 // shift window
		 view.getWindow().addMouseMotionListener(new MouseMotionAdapter() {
	            @Override
	            public void mouseDragged(MouseEvent e) { // moves the graph by the amount dragged by mouse
	                int dx = e.getX() - mousePt.x;
	                int dy = e.getY() - mousePt.y;
	                Camera camera = getCamera();
	                camera.setCameraX(camera.getCameraX()-dx/(double)MyWindow.getWidthWindow()* camera.getCameraWidth());
	                camera.setCameraY(camera.getCameraY()+dy/(double)MyWindow.getHeightWindow() * camera.getCameraHeight());
	                mousePt = e.getPoint();
	                view.getWindow().updateUI();
	                

	            }
	        });
	}
	private void updateButton() {
		JButton add = view.getAddButton();
		JButton remove = view.getRemoveButton();
		add.addActionListener(this);
		remove.addActionListener(this);
		add.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				InputPanel panel = view.getInputPanel();
				getGraphs().add(null);
		    	// update list
		    	panel.addText(new FunctionField());
				panel.updateButtons();
				updateFunctionField();				
			}});
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InputPanel panel = view.getInputPanel();
				// remove last graph 
		    	getGraphs().remove(panel.getTextBox().size()-1);
		    	// update list
		    	panel.removeText();
				panel.updateButtons();
				updateFunctionField();
				
			}});
		
	}
	private void updateFunctionField() {
		List<FunctionField> fFields = view.getFunctionField();
		for(int i = 0;i<fFields.size();i++) {
			FunctionField field = fFields.get(i);
			field.addKeyListener(this);
			field.addKeyListener(new KeyListener() {
				@Override
				public void keyReleased(KeyEvent e) {
					FunctionField field = (FunctionField) e.getSource();
					String function = field.getText();
					int index = field.getIndex();
					try {
						getModel().createGraph(index,new ExpressionImpl(function));
						getModel().updateGraph(index);
						field.setBackground(Color.WHITE);
					}catch(NotWellFormedFormulaException ex) {
						// syntax/semantic error 
						field.setBackground(Color.RED);
					}	
				}
				@Override
				public void keyTyped(KeyEvent e) {	}
				@Override
				public void keyPressed(KeyEvent e) {}
			});
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
