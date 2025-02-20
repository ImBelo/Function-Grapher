package model.parser;

import model.interfaces.TokenList;
import model.token.ClosedParentheses;
import model.token.NotWellFormedFormulaException;
import model.token.OpenParentheses;
import model.token.Product;
import model.token.Token;
import model.token.TokenType;
import model.token.Type;

public class ErrorFinderSemantic {
	public static boolean checkSemantic(TokenList tokens) {
		return AddOmittedMultiplication(tokens) && CheckOmittedParenthesesFunction(tokens);
	}
	public static boolean CheckOmittedParenthesesFunction(TokenList tokens) {
		for(int i = 0; i<tokens.length()-1;i++) { 
			Token curr = tokens.getToken(i);
			Token next = tokens.getToken(i==tokens.length()?null:i+1);
			Type currType = curr.getType();
			Type nextType = next.getType();
		/*	boolean isFunction = TokenType.getFunctions()
					.stream()
					.map(f->f.getData())
					.anyMatch(s->s.equals(nextData));
			boolean isNextVariable = TokenType.getVariables()
					.stream()
					.map(f->f.getData())
					.anyMatch(s->s.equals(nextData));
			boolean isCurrVariable = TokenType.getVariables()
					.stream()
					.map(f->f.getData())
					.anyMatch(s->s.equals(currData));*/
			
			// Ex.
			// logx+2*x-sinx
			// to log(x)+2*x-sin(x)
			if(TokenType.getFunctions().contains(currType) &&
				TokenType.getVariables().contains(nextType)) {
				 tokens.addTokenAt(++i,new OpenParentheses());
				 tokens.addTokenAt(i+=2,new ClosedParentheses());
			}
			
		}
		return true;
		
		
		
	}
	public static boolean AddOmittedMultiplication(TokenList tokens) {	
		
		for(int i = 0; i<tokens.length()-1;i++) {
	
				Token curr = tokens.getToken(i);
				Token next = tokens.getToken(i==tokens.length()?null:i+1);
				String currData = curr.getData();
				String nextData = next.getData();
				Type currType = curr.getType();
				Type nextType = next.getType();
				// Ex. 2x+2log(x)
				// to
				// 2*x+2*log(x)
				
				boolean isFunction = TokenType.getFunctions()
						.stream()
						.map(f->f.getData())
						.anyMatch(s->s.equals(nextData));
				boolean isNextVariable = TokenType.getVariables()
						.stream()
						.map(f->f.getData())
						.anyMatch(s->s.equals(nextData));
				boolean isCurrVariable = TokenType.getVariables()
						.stream()
						.map(f->f.getData())
						.anyMatch(s->s.equals(currData));
				
				if(curr!=null && curr.getType() == TokenType.NUMBER  &&
					isNextVariable ||
					(isCurrVariable) &&
					isFunction){
				   tokens.addTokenAt(++i,new Product(null,null));
				   
				
				   
				}
		}
		return true;


	}
}
