package Parser;

import Interfaces.Expression;
import Interfaces.Lexer;
import Interfaces.TokenList;
import Prova.DynamicSupplierTokenFactory;
import Prova.ExpressionImpl;
import Token.NotWellFormedFormulaException;
import Token.Token;

public class LexerImpl implements Lexer{
	
	public TokenList tokenize(Expression expr) {
		TokenList tokens = new TokenListImpl();
		// create shallow copy
		Expression exprLeft = new ExpressionImpl(expr.getExpression()); 
		
		if(!ErrorFinderSyntax.checkExpression(exprLeft))
			throw new NotWellFormedFormulaException(null,null);
				
		// extracts tokens from the left one by one
		while(!exprLeft.getExpression().isEmpty()) {
			Token token = DynamicSupplierTokenFactory.extractToken(exprLeft)
			.orElseThrow(()-> new NotWellFormedFormulaException("syntax error",null));
			tokens.addToken(token);
		}	
		return tokens;
	}

	
	
	
}
