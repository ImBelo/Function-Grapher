package model.factories;

import java.util.function.Supplier;

import model.token.AbstractBinaryFunction;
import model.token.Pow;

public class PowerSupplier implements Supplier<AbstractBinaryFunction> {

	@Override
	public Pow get() {
		return new Pow(null,null);
	}

}
