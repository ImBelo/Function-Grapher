package model.parser;
import java.util.ArrayList;
import java.util.List;

import model.interfaces.TokenList;
import model.token.Token;
public class TokenListImpl  implements TokenList{
		private List<Token> tokens;
		
		public TokenListImpl() {
			this.tokens = new ArrayList<Token>();		
			
		}
		public TokenListImpl(List<Token> tokens) {
			this.tokens = tokens;
		}
		public Token getToken(int i) {
			return tokens.get(i);
		}
		public void addToken(Token t) {
			tokens.add(t); 
		}	
		// given start and end index it splits the list
		public TokenList split(int start,int end){
			return new TokenListImpl(tokens.subList(start, end));	
		}
		public int length() {
			return tokens != null?this.tokens.size():0; 
			
		}
		public List<Token> getTokens(){
			return this.tokens;
		}
		@Override
		public void addTokenAt(int i, Token token) {
			tokens.add(i, token);
			
		}
		@Override
		public boolean equals(Object obj) {
			if (obj == this)
		            return true;
		            
		    if (obj == null || !(obj instanceof TokenList)) 
		            return false;
		    TokenList otherList = (TokenList) obj;
		        
			if(otherList.length() != this.length()) {
				return false;
			}
			for (int i = 0;i<otherList.length();i++) {
				if(!otherList.getToken(i).equals(this.getToken(i))) {
					return false;
				}
			}
			return true;		
		}
	

		
}

	



