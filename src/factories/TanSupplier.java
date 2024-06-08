package factories;

import java.util.function.Supplier;

import Token.AbstractUnaryFunction;
import Token.Tan;
import Token.Token;

public class TanSupplier implements Supplier<AbstractUnaryFunction> {

	@Override
	public Tan get() {
		return new Tan(null);
	}
	

}
