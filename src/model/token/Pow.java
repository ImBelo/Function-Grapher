package model.token;

public class Pow extends AbstractBinaryFunction{

	public Pow(Token left, Token right) {
		super(left, right);
		super.setType(TokenType.RAISED_TO);
	}

	@Override
	public double getValue() throws NotWellFormedFormulaException{
		super.isWellFormed("Power not well formed");
		double left = super.getLeft().getValue();
		double right = super.getRight().getValue();
		return left!=0 && right!=0?Math.pow(left, right):Double.NaN;
	}

	

}
