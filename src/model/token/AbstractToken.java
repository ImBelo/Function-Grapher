package model.token;
public abstract class AbstractToken implements Token{
	private Type type;
	public Type getType() { 
		return type;
	}
	public void setType(TokenType type) {
		this.type = type;
	}
	public abstract double getValue();
	public double realValue(Token t) {
		return t != null ? t.getValue() :Double.NaN;
	}
	
	
	


	

}
