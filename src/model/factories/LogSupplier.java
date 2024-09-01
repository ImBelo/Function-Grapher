package model.factories;

import java.util.function.Supplier;

import model.token.AbstractUnaryFunction;
import model.token.Log;

public class LogSupplier implements Supplier<AbstractUnaryFunction> {

	@Override
	public Log get() {
		return new Log(null);
	}

}
