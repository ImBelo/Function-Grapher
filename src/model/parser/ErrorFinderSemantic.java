package model.parser;

import model.interfaces.TokenList;
import model.token.NotWellFormedFormulaException;
import model.token.Product;
import model.token.Token;
import model.token.TokenType;

public class ErrorFinderSemantic {
	public static boolean checkSemantic(TokenList tokens) {
		return checkOperations(tokens);
	}
	// (x+2)
	public static boolean checkOperations(TokenList tokens) {	
		if (tokens == null)
			throw new NotWellFormedFormulaException(null, null);
		/*for(int i = 0; i<tokens.length();i++) {// i cicles token list
				TokenType left = i!=0?tokens.getToken(i-1).getType():null;
				TokenType right = i!=tokens.length()-1?tokens.getToken(i+1).getType():null;
				if (TokenType.getOperations().contains(tokens.getToken(i).getType())) {
					if(left == TokenType.OPEN_PARENTHESES ||
					   right == TokenType.CLOSED_PARENTHESES && 
					   (right == null || left == null));
						throw new NotWellFormedFormulaException(null, null);
				}
				
				
			}*/
		return true;
	}

	public static boolean checkNumber(TokenList tokens) {	
		/*
		for(int i = 0; i<tokens.length()-1;i++) {
			
				Token curr = tokens.getToken(i);
				Token next = tokens.getToken(i==tokens.length()?i:i+1);
				TokenType currType = curr.getType();
				TokenType nextType = next.getType();
				if(curr.getType() == TokenType.NUMBER ||
				   TokenType.getVariables().contains(currType) &&	
				   TokenType.getFunctions().contains(nextType) ||
				   TokenType.getVariables().contains(nextType) ||
				   TokenType.getOperations().contains(nextType)||
				   TokenType.OPEN_PARENTHESES == nextType) {
				   tokens.addTokenAt(++i,new Product(null,null));
				   break;
				
				   
			}
		}*/
		return true;
	}
	public static boolean checkUnaryFunctions(TokenList tokens) {	
		return true;
	}

}
