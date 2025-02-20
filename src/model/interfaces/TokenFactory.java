package model.interfaces;

import java.util.List;
import java.util.Optional;

import model.token.Token;
import model.token.Type;

public interface TokenFactory {
	public Optional<Token> extractToken(Expression expr);
	public void setDictionary(Type...types);
	public List<Type> getDictionary();

}
