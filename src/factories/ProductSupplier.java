package factories;

import java.util.function.Supplier;

import Token.AbstractBinaryFunction;
import Token.Product;
import Token.Token;

public class ProductSupplier implements Supplier<AbstractBinaryFunction> {

	@Override
	public Product get() {
		return new Product(null,null);
	}

}
