package model.interfaces;

import model.token.Token;

public interface Function {
	public double evaluateAt(double ...num);
	public Token getRoot();
	public void setRoot(Token t);

	

}
