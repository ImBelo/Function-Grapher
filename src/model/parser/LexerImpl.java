package model.parser;

import java.util.Optional;

import model.interfaces.Expression;
import model.interfaces.Lexer;
import model.interfaces.TokenList;
import model.main.DynamicSupplierTokenFactory;
import model.main.ExpressionImpl;
import model.token.NotWellFormedFormulaException;
import model.token.Token;

public class LexerImpl implements Lexer{
	
	public TokenList tokenize(Expression expr) {
		TokenList tokens = new TokenListImpl();
		// create shallow copy
		Expression exprLeft = new ExpressionImpl(expr.getExpression()); 
		
		if(!ErrorFinderSyntax.checkExpression(exprLeft))
			throw new NotWellFormedFormulaException("Syntax Error",null);
				
		// extracts tokens from the left one by one
		while(!exprLeft.getExpression().isEmpty()) {
			Token token = DynamicSupplierTokenFactory.extractToken(exprLeft)
			.orElseThrow(()-> new NotWellFormedFormulaException("syntax error",null));
			tokens.addToken(token);
		}	
		return tokens;
	}

	
	
	
}
