package model.token;



public class Log extends AbstractUnaryFunction{

	public Log(Token child) {
		super(child);
		super.setType(TokenType.LOG);
	}

	@Override
	public double getValue() {
		super.isWellFormed("Log not well formed");
		double child = realValue(super.getChild());
		return Math.log(child);
	}

	



}
