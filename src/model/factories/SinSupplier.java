package model.factories;

import java.util.function.Supplier;

import model.token.AbstractUnaryFunction;
import model.token.Sin;

public class SinSupplier implements Supplier<AbstractUnaryFunction> {

	@Override
	public Sin get() {
		return new Sin(null);
	}

}
