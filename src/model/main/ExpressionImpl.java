package model.main;

import model.interfaces.Expression;
import model.interfaces.Function;
import model.interfaces.Lexer;
import model.interfaces.Parser;
import model.parser.ParserImpl;
import model.token.VariableX;
import model.token.VariableY;

public class ExpressionImpl implements Expression{
	private String expression = "";
	private Arity arity;
	
	public ExpressionImpl(String expr) {
		this.expression = expr;
		arity = checkArity(expr);
	}

	public void setExpression(String expr) {
		this.expression = expr;
		arity = checkArity(expr);
	}
	

	public String getExpression() {
		return expression != null?expression:null;
	} 
	public static Arity checkArity(String expr) {
		if(expr == null)
			return Arity.Unary;
		if(expr.contains("y")) 
			return Arity.Binary;
		else 
			return Arity.Unary;
		
	}

	public Arity getArity() {
		return arity;
	}

	public void setArity(Arity arity) {
		this.arity = arity;
	}
}
