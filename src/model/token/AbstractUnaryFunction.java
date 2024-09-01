package model.token;

public abstract class AbstractUnaryFunction extends AbstractToken {
	private Token child;
	public AbstractUnaryFunction(Token child) {
		this.setChild(child);
	}
	
	public abstract double getValue();
	public Token getChild() {
		return child;
	} 

	public void setChild(Token child) {
		this.child = child;
	}
	public void isWellFormed(String message) {
		if(getChild() == null )
			throw new NotWellFormedFormulaException(message, null);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
            
		if (obj == null || !(obj instanceof AbstractUnaryFunction)) 
            return false;
		AbstractUnaryFunction otherToken = (AbstractUnaryFunction) obj;
		if(this.child == null && otherToken.getChild() == null)
			return true;
		if(this.child == null || otherToken.getChild() == null)
			return false;
		if(!this.child.equals(otherToken.getChild()) ) {
			return false;
		}	
		return true;   
		
		
	}

}
