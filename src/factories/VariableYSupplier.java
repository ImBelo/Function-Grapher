package factories;

import java.util.function.Supplier;

import Token.Token;
import Token.VariableY;

public class VariableYSupplier implements Supplier<Token> {

	@Override
	public Token get() {
		return (Token) VariableY.getInstance();
	}

}
