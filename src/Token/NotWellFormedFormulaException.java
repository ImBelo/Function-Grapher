package Token;

public class NotWellFormedFormulaException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotWellFormedFormulaException(String errorMessage,Throwable err) {
		super(errorMessage, err);
		
	}
	public NotWellFormedFormulaException(String errorMessage) {
		super(errorMessage);
	}
	public NotWellFormedFormulaException() {
		super();
	};

}
