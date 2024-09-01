package model.factories;

import java.util.function.Supplier;

import model.token.AbstractUnaryFunction;
import model.token.Cos;

public class CosSupplier implements Supplier<AbstractUnaryFunction> {

	@Override
	public Cos get() {
		return new Cos(null);
	}
	

}
