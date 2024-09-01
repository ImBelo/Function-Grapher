package model.factories;

import java.util.function.Supplier;

import model.token.AbstractUnaryFunction;
import model.token.Tan;

public class TanSupplier implements Supplier<AbstractUnaryFunction> {

	@Override
	public Tan get() {
		return new Tan(null);
	}
	

}
