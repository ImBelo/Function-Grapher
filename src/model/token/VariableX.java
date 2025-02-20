package model.token;

public class VariableX extends AbstractVariable{
	 private static Variable me;
	 

	    public VariableX() {
	    	super(VariableType.X);
	    }

	    public static Variable getInstance() {
	        if (me == null) {
	            me = new VariableX();
	        }

	        return me;
	    }
	 
	    public void setNum(double num) {
	    	super.setNum(num);
	    }
}
