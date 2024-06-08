package factories;

import java.util.function.Supplier;

import Token.ClosedParentheses;
import Token.Token;

public class ClosedParenthesesSupplier implements Supplier<Token> {

	@Override
	public Token get() {
		return new ClosedParentheses();
	}

}
