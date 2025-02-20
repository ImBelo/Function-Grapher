package view;

import java.util.List;

import controller.Controller;



public interface View {
	public Window getWindow();
	public Button getAddButton();
	public Button getRemoveButton();
	public Panel getInputPanel();
	public List<LinkedField<Field, Field>> getFunctionField();
	public void setController(Controller controller);
	public ErrorField getErrorField();

}
