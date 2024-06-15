package model.factories;

import java.util.function.Supplier;

import model.token.Token;
import model.token.VariableX;

public class VariableXSupplier implements Supplier<Token> {

	@Override
	public Token get() {
		return (Token) VariableX.getInstance();
	}

}
