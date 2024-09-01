package model.token;

public abstract class AbstractVariable extends MyNumber implements Variable{ 
	public AbstractVariable(TokenType t) {
		super(0.0);
		super.setType(t);
		
	}
	
	public void setValue(double num) {
		this.setNum(num);
	}
	@Override
	public double getValue() {
		return super.getValue();
	}
	

}
