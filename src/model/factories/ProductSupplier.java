package model.factories;

import java.util.function.Supplier;

import model.token.AbstractBinaryFunction;
import model.token.Product;

public class ProductSupplier implements Supplier<AbstractBinaryFunction> {

	@Override
	public Product get() {
		return new Product(null,null);
	}

}
