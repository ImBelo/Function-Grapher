package model.token;

public class Tan extends UnaryFunction {

	public Tan(Token child) {
		super(child);
		super.setType(FunctionType.TANGENT);
	}

	public Tan() {
		super.setType(FunctionType.TANGENT);
	}

	@Override
	public double getValue() {
		double child = realValue(super.getChild());
		return Math.tan(child);
	}

	

}
