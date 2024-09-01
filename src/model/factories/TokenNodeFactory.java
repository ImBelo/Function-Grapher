package model.factories;



import java.util.Optional;

import model.interfaces.TokenFinder;
import model.interfaces.TokenList;
import model.token.MyNumber;
import model.token.Token;
import model.token.TokenType;


public interface TokenNodeFactory{
	// Optional 
	public static Optional<Token> createTokenNode(TokenList tokens) {
		Optional<Token> token = Optional.empty();
		int location = 0;	// index of the token we are searching for
		if(tokens == null || tokens.getTokens().contains(null)) 
			return token;
		// inverse order for search: +,-,*,/,^,functions,parentheses,variables,numbers
		// find operations
		token = TokenFinder.findOperations(tokens);
		if(token.isPresent())
			return token;
		// find functions
		token = TokenFinder.findFunctions(tokens);
		if(token.isPresent())
			return token;
		// find parentheses
		token = TokenFinder.findParentheses(tokens);
		if(token.isPresent())
			return token;
		// find variables
		token = TokenFinder.findVariables(tokens);
		if(token.isPresent())
			return token;
		// find numbers
		location = TokenFinder.scanFromRight(tokens, TokenType.NUMBER);
		if (location != -1) {
			// creation of number
			return Optional.of(new MyNumber(tokens.getToken(location).getValue()));
		}	
		return token; 
	}
	
	
	
	

}