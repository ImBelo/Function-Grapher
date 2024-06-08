package factories;

import Interfaces.CartesianPlane;
import Interfaces.Expression;
import Prova.AbstractGraph;
import Prova.Arity;
import Prova.Graph;
import Prova.GraphSingleVar;
import Prova.GraphTwoVar;

public class GraphFactory {
	public static Graph createGraph(Expression expr) {
		Arity arity = expr.getArity(); 
		switch(arity) {
			case Unary:
				return new GraphSingleVar(expr);
			case Binary:
				return new GraphTwoVar(expr);
		}
		return null;
	}
}
