package model.token;

public interface Token {
	public double getValue() throws NotWellFormedFormulaException;

	public TokenType getType();

	

	
}
