package model.interfaces;

import java.util.Optional;

import model.token.Token;

public interface TokenTreeFactory {
	public Optional<Token> createTreeNode(TokenList tokens);

}
