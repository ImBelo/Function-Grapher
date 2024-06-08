package Token;

public enum TokenType {
	OPEN_PARENTHESES("("),
	CLOSED_PARENTHESES(")"),
	PLUS("+"),
	MINUS("-"),
	TIMES("*"),
	DIVIDED_BY("/"),
	RAISED_TO("^"),
	COMMA(","),
	X("x"),
	Y("y"),
	NUMBER(""), 
	SINE("sin"), 
	COSINE("cos"), 
	TANGENT("tan"), 
	LOG("log");
	public static final TokenType[] FUNCTIONS = {
			SINE, COSINE, TANGENT, LOG 
			};
	public static final TokenType[] OPERATIONS= {
			PLUS,MINUS,TIMES,DIVIDED_BY,RAISED_TO
	};
	public static final TokenType[] VARIABLES = {
			X,Y
	};
	private String data;
	private TokenType(String string) {
		this.setData(string);	
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	};
}
