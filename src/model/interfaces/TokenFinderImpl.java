package model.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

import model.main.TokenNodeFactory;
import model.token.NotWellFormedFormulaException;
import model.token.Token;
import model.token.TokenType;
import model.token.Type;

public class TokenFinderImpl implements TokenFinder {
	private TokenTreeFactory treeFactory;
	public TokenFinderImpl(TokenTreeFactory treeFactory) {
		this.treeFactory = treeFactory;
	}

	public Optional<Token> findToken(TokenList tokens){
		int location = 0 ;
		List<Type> types = TokenType.getTokens();
		for(Type type:types) {
			location = scanFromRight(tokens, type);
			if (location == -1) continue;
			if(TokenType.getFunctions().contains(type)) {
				TokenList inside = tokens.split(1, tokens.length());
				return TokenNodeFactory.createToken(treeFactory.createTreeNode(inside)
													.orElseThrow(()->new NotWellFormedFormulaException("can't parse inside function")), 
													type);
			}
			if(TokenType.getOperations().contains(type)) {
				TokenList left = tokens.split(0, location);
				TokenList right = tokens.split(location + 1, tokens.length());
				return TokenNodeFactory.createToken(treeFactory.createTreeNode(left)
													.orElseThrow(()->new NotWellFormedFormulaException("can't parse left side")),
													treeFactory.createTreeNode(right)
													.orElseThrow(()->new NotWellFormedFormulaException("can't parse right side")),
													type);
			}
			if(TokenType.getVariables().contains(type))
				return Optional.ofNullable(type.getSupplier().get());	
			boolean inParentheses = (tokens.length() >= 2 && tokens.getToken(tokens.length() - 1).getType() == TokenType.CLOSED_PARENTHESES
								  && tokens.getToken(0).getType() == TokenType.OPEN_PARENTHESES);
			if (inParentheses) {
				// list of token inside parentheses
				TokenList inside = tokens.split(1, tokens.length()-1);			
				// node creation
				return treeFactory.createTreeNode(inside);
			}
		}
		
		return Optional.empty();
		
	}

	public int scanFromRight(TokenList tokens, Type tokenType) {
		int openParentheses = 0;
		for (int i = tokens.length()-1; i >= 0;i--) {
			Token t = tokens.getToken(i);
			if (t != null) {
				if (t.getType() == TokenType.CLOSED_PARENTHESES) 
					openParentheses--;
				else if (t.getType() == TokenType.OPEN_PARENTHESES) 
					openParentheses++;
					// if openParentheses is 0 it means we are outside of parentheses
				if (t.getData().equals(tokenType.getData()) && openParentheses == 0) 		
					return i;
			}
		}
		return -1;
	}
	
}
