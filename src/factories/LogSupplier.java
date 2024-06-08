package factories;

import java.util.function.Supplier;

import Token.AbstractUnaryFunction;
import Token.Log;
import Token.Token;

public class LogSupplier implements Supplier<AbstractUnaryFunction> {

	@Override
	public Log get() {
		return new Log(null);
	}

}
