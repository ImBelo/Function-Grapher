package model.token;

public class UnaryFunction extends AbstractToken {
	private Token child;
	public UnaryFunction(Token child) {
		this.setChild(child);

	}
	public UnaryFunction() {
		super.setData("");
	}
	public UnaryFunction(String data) {
		super.setData(data);
	}

	public double getValue() {
		return 0;
	}
	
	public Token getChild() {
		return child;
	} 

	public void setChild(Token child) {
		this.child = child;
	}
	public void isWellFormed(String message) {
		if(getChild() == null )
			throw new NotWellFormedFormulaException(message);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
            
		if (obj == null || !(obj instanceof UnaryFunction)) 
            return false;
		UnaryFunction otherToken = (UnaryFunction) obj;
		if(this.child == null && otherToken.getChild() == null)
			return true;
		if(this.child == null || otherToken.getChild() == null)
			return false;
		if(!this.child.equals(otherToken.getChild()) ) {
			return false;
		}	
		return true;   
		
		
	}
	@Override
	public String getData() {
		return super.getData();
	}

}
