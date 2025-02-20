package model.main;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import model.token.BinaryFunction;
import model.token.Token;
import model.token.TokenType;
import model.token.Type;
import model.token.UnaryFunction;
import model.token.Variable;

public class DynamicTokenType implements Type{
	private static Map<String,Supplier<? extends Token>> map = new HashMap<>();
	static {
		for(Type type:TokenType.getTokens()) {
			map.put(type.getData(),type.getSupplier());
		}
	}
	private String data;
	private Supplier<? extends Token> supplier;
	public DynamicTokenType(String data, Supplier<? extends Token> supplier) {
		this.data = data;
		this.supplier = supplier;
	}
	public static Type createTokenType(String data,Supplier<? extends Token> supplier) {
		Type customType = new DynamicTokenType(data,supplier); 
		Token t = supplier.get();
		if(t instanceof UnaryFunction)
			TokenType.addFunction(customType);
		else if(t instanceof BinaryFunction) 
			TokenType.addOperation(customType);
		else if(t instanceof Variable) 
			TokenType.addVariable(customType);
		else
			throw new IllegalArgumentException("Wrong supplier");
		return customType;
		
	}
	@Override
	public String getData() {
		return this.data;
	}
	@Override
	public Supplier<? extends Token> getSupplier() {
		return this.supplier;
	}
	@Override
	public int getPriority() {
		return 6;
	}
	@Override
	public boolean equals(Object o) {
		Supplier<? extends Token> supp;
		if(!(o instanceof Type t))
			return false;
		else 
			supp = t.getSupplier();
		if(!this.getSupplier().get().equals(supp.get())) 
			return false;
		if(this.getData()!=t.getData())
			return false;
		return true;
		
	}
	


}
