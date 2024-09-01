package model.parser;

import model.factories.TokenNodeFactory;
import model.interfaces.Expression;
import model.interfaces.Function;
import model.interfaces.Lexer;
import model.interfaces.Parser;
import model.interfaces.TokenList;
import model.main.FunctionImpl;
import model.token.Token;
import model.token.Variable;
import model.token.VariableX;
import model.token.VariableY;


import model.token.NotWellFormedFormulaException;

public class ParserImpl implements Parser {
	private Lexer lexer = new LexerImpl();
	private TokenList tokens;
	private Variable x = VariableX.getInstance();
	private Variable y = VariableY.getInstance();
	public Function parse(Expression expr) { 
		// creates TokenList
		tokens = lexer.tokenize(expr);	
		Token root = null;
		if(!ErrorFinderSemantic.checkSemantic(tokens)){
			throw new NotWellFormedFormulaException("Semantic Error",null);
		}	
		// creates Abstrax Syntax Tree
		root = TokenNodeFactory.createTokenNode(tokens).orElseThrow(NotWellFormedFormulaException::new);	 
		// Abstract syntax tree
		return new FunctionImpl(root, x, y); // Abstract syntax tree containing expression
    }




}