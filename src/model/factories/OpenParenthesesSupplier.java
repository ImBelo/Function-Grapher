package model.factories;

import java.util.function.Supplier;

import model.token.OpenParentheses;
import model.token.Token;

public class OpenParenthesesSupplier implements Supplier<Token> {

	@Override
	public Token get() {
		return new OpenParentheses();
	}

}
