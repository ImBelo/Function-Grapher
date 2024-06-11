package model.factories;

import java.util.function.Supplier;

import model.token.AbstractBinaryFunction;
import model.token.Division;

public class DivisionSupplier implements Supplier<AbstractBinaryFunction> {

	@Override
	public Division get() {
		return new Division(null,null);
	}

}
