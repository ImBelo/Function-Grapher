package Interfaces;

import java.util.Optional;

import Prova.DynamicSupplierTokenFactory;
import Prova.DynamicSupplierTokenNodeFactory;
import Prova.ExpressionImpl;
import Token.NotWellFormedFormulaException;
import Token.Token;
import Token.TokenType;

import factories.TokenNodeFactoryImpl;

public interface TokenFinder {
	public static Optional<Token> findOperations(TokenList tokens) {
		int location = 0 ; 
		for(int l = 0; l<TokenType.OPERATIONS.length;l++) {
			location = scanFromRight(tokens, TokenType.OPERATIONS[l]);
			if (location != -1) {
				TokenList left = tokens.split(0, location);
				TokenList right = tokens.split(location + 1, tokens.length());
				// node creation
				return DynamicSupplierTokenNodeFactory.createToken(TokenNodeFactoryImpl.createTokenNode(left).orElseThrow(NotWellFormedFormulaException::new),
																   TokenNodeFactoryImpl.createTokenNode(right).orElseThrow(NotWellFormedFormulaException::new),
																   TokenType.OPERATIONS[l]);
			
			}
		}
		return Optional.empty();

	}

	public static Optional<Token> findFunctions(TokenList tokens) {
		int location = 0 ;
		for(int l = 0; l<TokenType.FUNCTIONS.length;l++) {
			location = scanFromRight(tokens, TokenType.FUNCTIONS[l]);
			if (location != -1) {
				TokenList inside = tokens.split(1, tokens.length());
				// node creation
				return DynamicSupplierTokenNodeFactory.createToken(TokenNodeFactoryImpl.createTokenNode(inside).orElseThrow(NotWellFormedFormulaException::new), 
													 				TokenType.FUNCTIONS[l]);
			
			}
		}
		return Optional.empty();
	}

	
	public static Optional<Token> findVariables(TokenList tokens) {
		int location = 0 ;
		for (int j = 0; j<TokenType.VARIABLES.length;j++) {
			location = scanFromRight(tokens, TokenType.VARIABLES[j]);
			if (location != -1) {
				// creazione del nodo variabile 
				return DynamicSupplierTokenFactory.extractToken(new ExpressionImpl(TokenType.VARIABLES[j].getData())) ;			
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
		return Optional.ofNullable(null);
	}
	public static int scanFromRight(TokenList tokens, TokenType tokenType) {
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
