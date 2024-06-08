package factories;

import java.util.function.Supplier;

import Token.AbstractBinaryFunction;
import Token.Sum;

public class SumSupplier implements Supplier<AbstractBinaryFunction> {

	@Override
	public Sum get() {
		return new Sum(null,null);
	}
	

}
