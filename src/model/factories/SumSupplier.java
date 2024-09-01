package model.factories;

import java.util.function.Supplier;

import model.token.AbstractBinaryFunction;
import model.token.Sum;

public class SumSupplier implements Supplier<AbstractBinaryFunction> {

	@Override
	public Sum get() {
		return new Sum(null,null);
	}
	

}
