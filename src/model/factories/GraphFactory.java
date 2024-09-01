package model.factories;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import model.interfaces.Expression;
import model.main.Arity;
import model.main.Graph;

public class GraphFactory{
	private static final Map<Arity,Supplier<Graph>> graphSuppliers = new HashMap<>();
	static {
		graphSuppliers.put(Arity.Unary, new GraphSingleVarSupplier());
		graphSuppliers.put(Arity.Binary, new GraphTwoVarSupplier());
	}
	public static void putGraph(Arity arity, Supplier<Graph> supplier) {
		graphSuppliers.put(arity,supplier);
	}
	public static Graph createGraph(Expression expr) {
		Arity arity = expr.getArity();
		Graph graph = graphSuppliers.get(arity).get();
		graph.setExpression(expr);
		return graph;
	}
}
