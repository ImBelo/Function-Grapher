package factories;

import java.util.function.Supplier;

import Token.Token;
import Token.VariableX;

public class VariableXSupplier implements Supplier<Token> {

	@Override
	public Token get() {
		return (Token) VariableX.getInstance();
	}

}
