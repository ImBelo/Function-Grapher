package model.token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Supplier;

public enum TokenType implements Type{
	OPEN_PARENTHESES("(",OpenParentheses::new,10),
	CLOSED_PARENTHESES(")",ClosedParentheses::new,11),
	NUMBER(" ",()->new MyNumber(0.0),14); 
	
	private static List<Type> FUNCTIONS  = new ArrayList<>(Arrays.asList(FunctionType.values()));
	private static List<Type> OPERATIONS = new ArrayList<>(Arrays.asList(OperationType.values()));
	private static List<Type> VARIABLES  = new ArrayList<>(Arrays.asList(VariableType.values()));
	// lazy evaluation of numberToken
	private static Supplier<Integer> getNumberToken = ()->FUNCTIONS.size()+OPERATIONS.size()+VARIABLES.size();
	private static PriorityQueue<Type> queue;  
	
	public static List<Type> getTokens() {
		int numToken = getNumberToken.get(); 
		boolean needUpdate = queue == null || queue.size() != numToken;
		if (needUpdate) {
			queue = new PriorityQueue<Type>(numToken,(o1,o2)->o1.getPriority()-o2.getPriority());
			queue.addAll(FUNCTIONS);
			queue.addAll(OPERATIONS);
			queue.addAll(VARIABLES);
			queue.addAll(Arrays.asList(TokenType.values()));
		}
		return new ArrayList<>(queue);
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
	public static void addFunction(Type function) {
		if(function.getSupplier().get() instanceof UnaryFunction)
			FUNCTIONS.add(function);
		else 
			throw new IllegalArgumentException("This is not a function");
		
	}
	public static void addOperation(Type operation) {
		if(operation.getSupplier().get() instanceof BinaryFunction)
			OPERATIONS.add(operation);
		else 
			throw new IllegalArgumentException("This is not an operation");
		
	}
	public static void addVariable(Type variable) {
		if(variable.getSupplier().get() instanceof Variable )
			VARIABLES.add(variable);
		
		else 
			throw new IllegalArgumentException("This is not a variable");
		
	} 
	private String data;
	private Supplier<Token> supplier;
	private int priority;
	private TokenType(String string){
		this.data = string;	
	}
	private TokenType(String string,Supplier<Token> supp,int priority) {
		this.data = string;	
		this.supplier = supp;
		this.priority = priority;
	}
	public int getPriority() {
		return priority;
	}
	@Override
	public String getData() {
		return data;
	}
	public Supplier<Token> getSupplier(){
		return this.supplier;
	}

}
