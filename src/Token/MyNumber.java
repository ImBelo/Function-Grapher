package Token;

public class MyNumber extends AbstractToken implements Token{
	private double num;
	public MyNumber(double num){
		this.setNum(num);
		super.setType(TokenType.NUMBER);
	}
	public void setNum(double num) {
		this.num = num; 
	}
	@Override
	public double getValue() {
		return this.num;
	}
	@Override
	public Token create() {
		return new MyNumber(0.0);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
            
		if (obj == null || !(obj instanceof MyNumber)) 
            return false;
		MyNumber otherNumber = (MyNumber) obj;
		if(this.num != otherNumber.getValue())
			return false;   
		return true;
	}
	
}
