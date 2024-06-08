package factories;

import java.util.function.Supplier;

import Token.AbstractBinaryFunction;
import Token.Pow;
import Token.Token;

public class PowerSupplier implements Supplier<AbstractBinaryFunction> {

	@Override
	public Pow get() {
		return new Pow(null,null);
	}

}
