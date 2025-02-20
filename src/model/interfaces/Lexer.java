package model.interfaces;

import model.token.NotWellFormedFormulaException;

public interface Lexer{
	public TokenList tokenize(Expression expr) throws NotWellFormedFormulaException;
}
