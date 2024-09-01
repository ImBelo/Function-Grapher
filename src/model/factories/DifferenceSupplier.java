package model.factories;

import java.util.function.Supplier;

import model.token.AbstractBinaryFunction;
import model.token.Difference;

public class DifferenceSupplier implements Supplier<AbstractBinaryFunction> {

	@Override
	public Difference get() {
		return new Difference(null,null);
	}

}
