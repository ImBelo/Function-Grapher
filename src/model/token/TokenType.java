package model.token;

import java.util.List;

public enum TokenType implements Type{
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
	private static List<Type> FUNCTIONS = List.of(SINE,COSINE,TANGENT,LOG);
	private static List<Type> OPERATIONS = List.of(PLUS,MINUS,TIMES,DIVIDED_BY,RAISED_TO);
	private static List<Type> VARIABLES = List.of(X,Y);
	// methods to add new types, should be used in another class
	public static void addFunction(Type type) {
		FUNCTIONS.add(type);
	}
	public static void addOperation(Type type) {
		OPERATIONS.add(type);
	}
	
	public static List<Type> getOperations(){
		return List.copyOf(OPERATIONS);
	}
	public static List<Type> getFunctions(){
		return List.copyOf(FUNCTIONS);
	}
	public static List<Type> getVariables(){
		return List.copyOf(VARIABLES);
	}
	private String data;
	private TokenType(String string) {
		this.data = string;	
	}
	public String getData() {
		return data;
	}

}
