package factories;

import java.util.function.Supplier;

import Token.AbstractUnaryFunction;
import Token.Cos;
import Token.Token;

public class CosSupplier implements Supplier<AbstractUnaryFunction> {

	@Override
	public Cos get() {
		return new Cos(null);
	}
	

}
