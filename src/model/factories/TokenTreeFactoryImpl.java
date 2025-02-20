package model.factories;



import java.util.Optional;

import model.interfaces.TokenFinder;
import model.interfaces.TokenFinderImpl;
import model.interfaces.TokenList;
import model.interfaces.TokenTreeFactory;
import model.token.MyNumber;
import model.token.NotWellFormedFormulaException;
import model.token.Token;
import model.token.TokenType;


public class TokenTreeFactoryImpl implements TokenTreeFactory{
	// Optional
	@Override
	public Optional<Token> createTreeNode(TokenList tokens) {
		TokenFinder tokenFinder = new TokenFinderImpl(this);
		Optional<Token> token = Optional.empty();
		int location = 0;	// index of the token we are searching for
		if(tokens == null || tokens.getTokens().contains(null)) 
			return token;	
		token = tokenFinder.findToken(tokens);
		if(token.isPresent())
			return token;
		location = tokenFinder.scanFromRight(tokens, TokenType.NUMBER);
		if (location != -1) {
			// creation of number
			return Optional.of(new MyNumber(tokens.getToken(location).getValue()));
		}	
		if(token.isEmpty())
			throw new NotWellFormedFormulaException("Cant find token");
		return token; 
	}
	
	
	
	

}