package model.parser;

import model.interfaces.Expression;
import model.interfaces.Function;
import model.interfaces.Lexer;
import model.interfaces.Parser;
import model.interfaces.TokenList;
import model.main.FunctionImpl;
import model.token.Token;
import model.token.TokenType;
import model.token.Type;
import model.token.Variable;
import model.token.VariableX;
import model.token.VariableY;

import java.util.List;
import java.util.Set;

import model.factories.TokenTreeFactoryImpl;
import model.interfaces.*;

import model.token.NotWellFormedFormulaException;

public class ParserImpl implements Parser {
	private Lexer lexer;
	private TokenTreeFactory tnf;
	private TokenList tokens;
	private List<Type> variables = TokenType.getVariables();
	public ParserImpl(Lexer lexer) {
		this.lexer = lexer;
	}
	public Function parse(Expression expr) {
		tnf = new TokenTreeFactoryImpl();
		// creates TokenList
		tokens = lexer.tokenize(expr);	
		Token root = null;
	//	if(!ErrorFinderSemantic.checkSemantic(tokens)){
	//		throw new NotWellFormedFormulaException("Semantic Error");
	//	}	
		// creates Abstrax Syntax Tree
		root = tnf.createTreeNode(tokens).orElseThrow(()->new NotWellFormedFormulaException("cant Parse"));	 
		// Abstract syntax tree
		return new FunctionImpl(root, variables); // Abstract syntax tree containing expression
    }
	public void setVariables(List<Type> variables) {
		this.variables = variables;
	}




}