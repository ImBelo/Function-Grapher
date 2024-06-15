package model.token;

public class Difference extends AbstractBinaryFunction{

	public Difference(Token left,Token right) {
		super(left, right);
		this.setType(TokenType.MINUS);
	}

	@Override
	public double getValue() {
		super.isWellFormed("Difference not well formed");
		double left = realValue(super.getLeft());
		double right = realValue(super.getRight());
		return left-right;
	}

	@Override
	public Token create() {
		return new Difference(null,null);
	}

	@Override
	public Token createToken(Token left, Token right) {
		return new Difference(left,right);
	}

}
