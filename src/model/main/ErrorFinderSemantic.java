package model.main;

import model.interfaces.TokenList;
import model.token.NotWellFormedFormulaException;
import model.token.TokenType;

public class ErrorFinderSemantic {
	public static boolean checkSemantic(TokenList tokens) {
		checkNumber(tokens);
		return checkOperations(tokens);
	}
	public static boolean checkOperations(TokenList tokens) {	
		if (tokens == null)
			throw new NotWellFormedFormulaException(null, null);
		for(int i = 0; i<tokens.length();i++) {// i cicles token list
			for(int l=0;l<TokenType.OPERATIONS.length;l++) { // l cicles possible operations
				TokenType left = i!=0?tokens.getToken(i-1).getType():null;
				TokenType right = i!=tokens.length()-1?tokens.getToken(i+1).getType():null;
				if (tokens.getToken(i).getType() == TokenType.OPERATIONS[l]) {
					if(left== TokenType.OPEN_PARENTHESES ||
					   right== TokenType.CLOSED_PARENTHESES && 
					   (right == null || left == null) )
						throw new NotWellFormedFormulaException(null, null);
				}
				
				
			}
			
			
		}
		return true;
	}
	
	public static boolean checkNumber(TokenList tokens) {	
		/*for(int i = 0; i<tokens.length()-1;i++) {
			for(int l = 0; l< TokenType.FUNCTIONS.length;l++) {
				Token curr = tokens.getToken(i);
				Token next = tokens.getToken(i+1);
				if(curr.getType() == TokenType.NUMBER ||
				   curr.getType() == TokenType.X ||
				   curr.getType() == TokenType.Y &&	
				   next.getType() == TokenType.FUNCTIONS[l] ||
				   next.getType() == TokenType.X||
				   next.getType() == TokenType.Y) {
				   tokens.addTokenAt(++i,new Product(null,null));
				   break;
				}
				   
			}
		}*/
		return true;
	}
	public static boolean checkUnaryFunctions(TokenList tokens) {	
		return true;
	}

}
