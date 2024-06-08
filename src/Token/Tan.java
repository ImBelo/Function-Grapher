package Token;

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

	@Override
	public Token create() {
		return new Tan(null);
	}

	@Override
	public Token createToken(Token child) {
		return new Tan(child);
	}

}
