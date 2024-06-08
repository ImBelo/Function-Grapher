package Prova;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import Interfaces.Expression;
import Token.AbstractUnaryFunction;
import Token.Token;
import factories.ClosedParenthesesSupplier;
import factories.CosSupplier;
import factories.DifferenceSupplier;
import factories.DivisionSupplier;
import factories.LogSupplier;
import factories.NumberFactoryImpl;
import factories.OpenParenthesesSupplier;
import factories.PowerSupplier;
import factories.ProductSupplier;
import factories.SinSupplier;
import factories.SumSupplier;
import factories.TanSupplier;
import factories.VariableXSupplier;
import factories.VariableYSupplier;

public class DynamicSupplierTokenFactory {
	private static final Map<String,Supplier<? extends Token>> registeredSuppliers = new HashMap<>();
	static {
		registeredSuppliers.put("+", new SumSupplier());
		registeredSuppliers.put("-", new DifferenceSupplier());
		registeredSuppliers.put("/", new DivisionSupplier());
		registeredSuppliers.put("*", new ProductSupplier());
		registeredSuppliers.put("^", new PowerSupplier());
		registeredSuppliers.put("log", new LogSupplier());
		registeredSuppliers.put("sin", new SinSupplier());
		registeredSuppliers.put("cos", new CosSupplier());
		registeredSuppliers.put("tan", new TanSupplier());
		registeredSuppliers.put(")", new ClosedParenthesesSupplier());
		registeredSuppliers.put("(", new OpenParenthesesSupplier());
		registeredSuppliers.put("x", new VariableXSupplier());
		registeredSuppliers.put("y", new VariableYSupplier());
	}
	public static void registerSupplier(String data,Supplier<? extends Token> supplier) {
		registeredSuppliers.put(data,supplier);
	}
	
	public static Optional<Token> extractToken(Expression expr) {
		Character currentChar = expr.getExpression().charAt(0);
		
		if (Character.isDigit(currentChar)) 
			return NumberFactoryImpl.createNumber(expr);
		
		for(var entry: registeredSuppliers.entrySet()){
			String key = entry.getKey();
			if(expr.getExpression().startsWith(key)) {
				expr.setExpression(expr.getExpression().substring(key.length()));
				return Optional.ofNullable(registeredSuppliers.get(key).get());
			}
			
		}
			
		return Optional.ofNullable(null);
		
	}

}
