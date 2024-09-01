package model.token;

public class Tan extends AbstractUnaryFunction {

	public Tan(Token child) {
		super(child);
		super.setType(TokenType.TANGENT);
	}

	@Override
	public double getValue() {
		if(super.getChild() == null)
			throw new NotWellFormedFormulaException(null,null);
		return Math.tan(super.getChild().getValue());
	}

	

}
