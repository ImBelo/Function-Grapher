package model.token;



public abstract class BinaryFunction extends AbstractToken{
	private Token right;
	private Token left;
	
	public BinaryFunction(Token left,Token right) {
		this.setLeft(left);
		this.setRight(right);
	}
	public BinaryFunction() {
	}
	public void setType(Type type) {
		super.setType(type);
		super.setData(type.getData());
	}
	public double getValue() {
		return 0;
	}
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
			throw new NotWellFormedFormulaException(message);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
            
		if (obj == null || !(obj instanceof BinaryFunction)) 
            return false;
     BinaryFunction otherToken = (BinaryFunction) obj;
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
