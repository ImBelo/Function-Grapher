package factories;

import java.util.function.Supplier;

import Token.AbstractUnaryFunction;
import Token.Sin;
import Token.Token;

public class SinSupplier implements Supplier<AbstractUnaryFunction> {

	@Override
	public Sin get() {
		return new Sin(null);
	}

}
