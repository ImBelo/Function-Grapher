package Token;

public class OpenParentheses extends AbstractParentheses{
	public OpenParentheses() {
		this.setType(TokenType.OPEN_PARENTHESES);
	}
	@Override
	public Token create() {
		return new OpenParentheses();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
            
		if (obj == null || !(obj instanceof OpenParentheses)) 
            return false;
		return true;   
		
	}
}
