package Token;


public class Sin extends AbstractUnaryFunction {

	public Sin(Token child) {
		super(child);
		super.setType(TokenType.SINE);
	}

	@Override
	public double getValue() throws NotWellFormedFormulaException{
		super.isWellFormed("Sine not well formed");
		return Math.sin(super.getChild().getValue());
	}

	@Override
	public Token create() {
		return new Sin(null);
	}

	@Override
	public Token createToken(Token child) {
		return new Sin(child);
	}

}
