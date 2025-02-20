package view;

import controller.Controller;

public interface Window{

	void setBounds(int iNPUTPANELX, int i, int j, int fRAMEY);
	int getWidth();
	int getHeight();
	void setController(Controller controller);
}
