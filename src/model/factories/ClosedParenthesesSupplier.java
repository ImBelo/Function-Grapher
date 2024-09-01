package model.factories;

import java.util.function.Supplier;

import model.token.ClosedParentheses;
import model.token.Token;

public class ClosedParenthesesSupplier implements Supplier<Token> {

	@Override
	public Token get() {
		return new ClosedParentheses();
	}

}
