package Token;



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

	@Override
	public Token create() {
		return new Log(null);
	}

	@Override
	public Token createToken(Token child) {
		return new Log(child);
	}



}
