package model.token;

public class VariableY extends AbstractVariable implements Variable{
	 private static VariableY me;
	 

	    public VariableY() {
	    	super(TokenType.Y);
	    }

	    public static Variable getInstance() {
	        if (me == null) {
	            me = new VariableY();
	        }

	        return me;
	    }
	    public Token create() {
	    	return (Token)getInstance();
	    }
	    
	    public void setNum(double num) {
	    	super.setNum(num);
	    }

	

	
	  
}
