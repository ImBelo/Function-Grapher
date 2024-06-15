package model.factories;

import java.util.function.Supplier;

import model.token.Token;
import model.token.VariableY;

public class VariableYSupplier implements Supplier<Token> {

	@Override
	public Token get() {
		return (Token) VariableY.getInstance();
	}

}
