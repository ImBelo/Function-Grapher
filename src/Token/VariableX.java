package Token;

public class VariableX extends AbstractVariable{
	 private static VariableX me;
	 

	    public VariableX() {
	    	super(TokenType.X);
	    }

	    public static Variable getInstance() {
	        if (me == null) {
	            me = new VariableX();
	        }

	        return me;
	    }
	    public Token create(Token t,Token tf ) {
	    	return (Token)getInstance();
	    }
	    public void setNum(double num) {
	    	super.setNum(num);
	    }
}
