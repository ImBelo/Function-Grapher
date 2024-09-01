package model.token;


public class Product extends AbstractBinaryFunction{

	public Product(Token left, Token right) {
		super(left, right);
		this.setType(TokenType.TIMES);
	}
	public double getValue() {
		super.isWellFormed("Product not well formed");
		double left = realValue(super.getLeft());
		double right = realValue(super.getRight());
		return left*right;
	}


}
