package model.factories;

import java.util.function.Supplier;

import model.main.Graph;
import model.main.GraphSingleVar;

public class GraphSingleVarSupplier implements Supplier<Graph>{
	@Override
	public Graph get() {
		return new GraphSingleVar(null);
	}

}
