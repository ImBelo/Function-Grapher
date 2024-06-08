package factories;

import java.util.function.Supplier;

import Token.OpenParentheses;
import Token.Token;

public class OpenParenthesesSupplier implements Supplier<Token> {

	@Override
	public Token get() {
		return new OpenParentheses();
	}

}
