package model.interfaces;

import java.util.Optional;

import model.token.Token;
import model.token.Type;

public interface TokenFinder {
	public int scanFromRight(TokenList tokens, Type tokenType);
	public Optional<Token> findToken(TokenList tokens);
	
}
