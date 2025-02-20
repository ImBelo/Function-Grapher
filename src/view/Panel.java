package view;

import java.awt.Component;
import java.util.List;

import controller.Controller;

public interface Panel {
	void updateButtons();
	List<LinkedField<Field, Field>> getTextBox();
	void removeText();
	int getWidth();
	int getHeight();
	Button getAddButton();
	Button getRemoveButton();
	void addText(LinkedField<Field, Field> pair);
	void setController(Controller controller);

}
