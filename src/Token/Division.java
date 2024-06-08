package Token;


public class Division extends AbstractBinaryFunction {

	public Division(Token left, Token right) {
		super(left, right);
		super.setType(TokenType.DIVIDED_BY);
	}

	@Override
	public double getValue() throws NotWellFormedFormulaException{
		super.isWellFormed("Division not well formed ");
		double numerator = super.realValue(getLeft());
		double denominator = super.realValue(getRight());
		return denominator != 0? numerator/denominator:Double.NaN;
	}

	@Override
	public Token create() {
		return new Division(null,null);
	}

	@Override
	public Token createToken(Token left, Token right) {
		return new Division(left,right);
	}

}
