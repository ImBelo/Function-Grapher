package model.parser;

import java.util.List;

import model.interfaces.Expression;
import model.interfaces.Lexer;
import model.interfaces.TokenFactory;
import model.interfaces.TokenList;
import model.main.ExpressionImpl;
import model.main.TokenFactoryImpl;
import model.token.NotWellFormedFormulaException;
import model.token.Token;
import model.token.Type;

public class LexerImpl implements Lexer {
	TokenFactory tokenFactory;
	public LexerImpl(TokenFactory tokenFactory) {
		this.tokenFactory=tokenFactory;
	}
	public TokenList tokenize(Expression expr){
		if(expr == null) expr = new ExpressionImpl("0");
		TokenList tokens = new TokenListImpl();
		// create shallow copy
		Expression exprLeft = new ExpressionImpl(expr.getExpression()); 
		
		if(!ErrorFinderSyntax.checkExpression(exprLeft))
			throw new NotWellFormedFormulaException("Syntax Error");
				
		// extracts tokens from the left one by one
		while(!exprLeft.getExpression().isEmpty()) {
			Token token = tokenFactory.extractToken(exprLeft)
			.orElseThrow(()-> new NotWellFormedFormulaException("Syntax error"));
			tokens.addToken(token);
		}	
		return tokens;
	}

	
	
	
}
