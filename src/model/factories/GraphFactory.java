package model.factories;
import model.interfaces.Expression;
import model.main.Arity;
import model.main.Graph;
import model.main.GraphSingleVar;
import model.main.GraphTwoVar;

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
