package view;

import java.awt.Component;
import java.util.List;

public interface Panel {

	void addText(Field field);
	void updateButtons();
	List<Field> getTextBox();
	void removeText();
	int getWidth();
	int getHeight();
	Button getAddButton();
	Button getRemoveButton();

}
