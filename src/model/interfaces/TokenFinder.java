package model.interfaces;

import java.util.Optional;

import model.factories.TokenNodeFactoryImpl;
import model.main.DynamicSupplierTokenFactory;
import model.main.DynamicSupplierTokenNodeFactory;
import model.main.ExpressionImpl;
import model.token.NotWellFormedFormulaException;
import model.token.Token;
import model.token.TokenType;
import model.token.Type;

public interface TokenFinder {
	public static Optional<Token> findOperations(TokenList tokens) {
		int location = 0 ; 
		for(int l = 0; l<TokenType.getOperations().size();l++) {
			location = scanFromRight(tokens, TokenType.getOperations().get(l));
			if (location != -1) {
				TokenList left = tokens.split(0, location);
				TokenList right = tokens.split(location + 1, tokens.length());
				// node creation
				return DynamicSupplierTokenNodeFactory.createToken(TokenNodeFactoryImpl.createTokenNode(left).orElseThrow(NotWellFormedFormulaException::new),
																   TokenNodeFactoryImpl.createTokenNode(right).orElseThrow(NotWellFormedFormulaException::new),
																   TokenType.getOperations().get(l));
			
			}
		}
		return Optional.empty();

	}

	public static Optional<Token> findFunctions(TokenList tokens) {
		int location = 0 ;
		for(int l = 0; l<TokenType.getFunctions().size();l++) {
			location = scanFromRight(tokens, TokenType.getFunctions().get(l));
			if (location != -1) {
				TokenList inside = tokens.split(1, tokens.length());
				// node creation
				return DynamicSupplierTokenNodeFactory.createToken(TokenNodeFactoryImpl.createTokenNode(inside).orElseThrow(NotWellFormedFormulaException::new), 
													 				TokenType.getFunctions().get(l));
			
			}
		}
		return Optional.empty();
	}

	
	public static Optional<Token> findVariables(TokenList tokens) {
		int location = 0 ;
		for (int j = 0; j<TokenType.getVariables().size();j++) {
			location = scanFromRight(tokens, TokenType.getVariables().get(j));
			if (location != -1) {
				// creation variable node
				return DynamicSupplierTokenFactory.extractToken(new ExpressionImpl(TokenType.getVariables().get(j).getData())) ;			
			}
		}	
		return Optional.empty();
	}

	
	public static Optional<Token> findParentheses(TokenList tokens) {
		boolean inParentheses = (tokens.length() >= 2 && tokens.getToken(tokens.length() - 1).getType() == TokenType.CLOSED_PARENTHESES
				&& tokens.getToken(0).getType() == TokenType.OPEN_PARENTHESES);
		if (inParentheses) {
			// list of token inside parentheses
			TokenList inside = tokens.split(1, tokens.length()-1);
	
			// node creation
			return TokenNodeFactoryImpl.createTokenNode(inside);
		}
		return Optional.empty();
	}
	public static int scanFromRight(TokenList tokens, Type tokenType) {
		int openParentheses = 0;
		for (int i = tokens.length()-1; i >= 0;i--) {
			Token t = tokens.getToken(i);
			
			if (t != null) {
				if (t.getType() == TokenType.CLOSED_PARENTHESES) {
					openParentheses--;
				} else if (t.getType() == TokenType.OPEN_PARENTHESES) {
					openParentheses++;
					// if openParentheses is 0 it means we are outside of parenthesis
				} else if (t.getType() == tokenType && openParentheses == 0) {
					
					return i;
				}
			}
			
		}
		return -1;
		}
}
