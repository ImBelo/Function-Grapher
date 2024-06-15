package model.token;




public class Sum extends AbstractBinaryFunction{
	public Sum(Token left, Token right) {
		super(left, right);
		super.setType(TokenType.PLUS);
	}

	@Override
	public double getValue() {
		super.isWellFormed("sum not well formed");
		double left = realValue(super.getLeft());
		double right = realValue(super.getRight());
		return left + right;
	}

	@Override
	public Token create() {
		return new Sum(null,null); 
	}

	@Override
	public Token createToken(Token left, Token right) {
		return new Sum(left,right);
	}

	
	
}
