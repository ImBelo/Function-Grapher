package factories;

import java.util.function.Supplier;

import Token.AbstractBinaryFunction;
import Token.Difference;

public class DifferenceSupplier implements Supplier<AbstractBinaryFunction> {

	@Override
	public Difference get() {
		return new Difference(null,null);
	}

}
