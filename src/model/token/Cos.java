package model.token;

public class Cos extends AbstractUnaryFunction {

	public Cos(Token child) {
		super(child);
		super.setType(TokenType.COSINE);
	}

	@Override 
	public double getValue() {
		super.isWellFormed("cosine not well formed");
		double child = realValue(super.getChild());
		return Math.cos(child);
	}


}
