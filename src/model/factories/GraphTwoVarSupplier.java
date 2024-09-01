package model.factories;

import java.util.function.Supplier;

import model.main.Graph;
import model.main.GraphTwoVar;

public class GraphTwoVarSupplier implements Supplier<Graph> {

	@Override
	public Graph get() {
		return new GraphTwoVar(null);
	}

}
