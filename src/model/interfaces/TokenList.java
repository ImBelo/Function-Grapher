package model.interfaces;

import java.util.List;
import model.token.Token;


public interface TokenList {

	void addToken(Token token);

	int length();

	List<Token> getTokens();

	TokenList split(int i, int location);

	Token getToken(int i);

	void addTokenAt(int i, Token token);

	boolean equals(Object t);

	

}
