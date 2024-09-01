package view;

import java.util.List;



public interface View {
	Window getWindow();
	Button getAddButton();
	Button getRemoveButton();
	Panel getInputPanel();
	List<Field> getFunctionField();

}
