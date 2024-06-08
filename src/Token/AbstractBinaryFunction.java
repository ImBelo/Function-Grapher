package Token;



public abstract class AbstractBinaryFunction extends AbstractToken{
	private Token right;
	private Token left;
	
	public AbstractBinaryFunction(Token left,Token right) {
		this.setLeft(left);
		this.setRight(right);
	}
	public abstract double getValue();
	public abstract Token createToken(Token left,Token right);
	public Token getRight() {
		return right;
	}

	public void setRight(Token right) {
		this.right = right;
	}

	public Token getLeft() {
		return left;
	}
	public void setLeft(Token left) {
		this.left = left;
	}
	protected void isWellFormed(String message) {
		if(getLeft() == null || getRight() == null)
			throw new NotWellFormedFormulaException(message, null);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
            
		if (obj == null || !(obj instanceof AbstractBinaryFunction)) 
            return false;
     AbstractBinaryFunction otherToken = (AbstractBinaryFunction) obj;
     if(this.left == null && this.right == null && 
    	otherToken.getLeft() == null && otherToken.getRight() == null)
    	 return true;
     if(this.left == null || this.right == null ||
    	otherToken.getLeft() == null || otherToken.getRight() == null)
    	    	 return false;
     if(this.left.equals(otherToken.getLeft()) &&
    	this.right.equals(otherToken.getRight()) ) {
    	 return true;
     }
    	 return false;   
		
	}
	
}
