package factories;

import java.util.function.Supplier;

import Token.AbstractBinaryFunction;
import Token.Division;

public class DivisionSupplier implements Supplier<AbstractBinaryFunction> {

	@Override
	public Division get() {
		return new Division(null,null);
	}

}
