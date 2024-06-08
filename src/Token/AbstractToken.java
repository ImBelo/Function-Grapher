package Token;
public abstract class AbstractToken implements Token{
	private TokenType type;
	public TokenType getType() { 
		return type;
	}
	public void setType(TokenType type) {
		this.type = type;
	}
	public abstract double getValue() throws NotWellFormedFormulaException;
	public double realValue(Token t) {
		return t != null ? t.getValue() :Double.NaN;
	}
	
	public abstract Token create();


	

}
