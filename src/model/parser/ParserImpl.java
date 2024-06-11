package model.parser;

import model.factories.TokenNodeFactoryImpl;
import model.interfaces.Expression;
import model.interfaces.Function;
import model.interfaces.Lexer;
import model.interfaces.Parser;
import model.interfaces.TokenList;
import model.main.ErrorFinderSemantic;
import model.main.FunctionImpl;
import model.token.Token;
import model.token.Variable;
import model.token.VariableX;
import model.token.VariableY;

import java.util.Optional;

import model.token.NotWellFormedFormulaException;

public  class ParserImpl implements Parser {
	private Lexer lexer = new LexerImpl();
	private TokenList tokens;
	private static Variable x = VariableX.getInstance();
	private static Variable y = VariableY.getInstance();
	public Optional<Function> parse(Expression expr) { 
		 
		// creates TokenList
		
		tokens = lexer.tokenize(expr);
		
		Token root = null;
		if(!ErrorFinderSemantic.checkSemantic(tokens)){
			throw new NotWellFormedFormulaException(null,null);
		}
		if (tokens != null) {
			// creates Abstrax Syntax Tree
			 root = TokenNodeFactoryImpl.createTokenNode(tokens).orElseThrow(NotWellFormedFormulaException::new);	 
		}
		return Optional.ofNullable(new FunctionImpl(root, x, y)); // abstract syntax tree containing the expression
    }




}