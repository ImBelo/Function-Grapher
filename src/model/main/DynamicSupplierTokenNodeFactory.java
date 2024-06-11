package model.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import model.token.AbstractBinaryFunction;
import model.token.AbstractUnaryFunction;
import model.factories.CosSupplier;
import model.factories.DifferenceSupplier;
import model.factories.DivisionSupplier;
import model.factories.LogSupplier;
import model.factories.PowerSupplier;
import model.factories.ProductSupplier;
import model.factories.SinSupplier;
import model.factories.SumSupplier;
import model.factories.TanSupplier;
import model.token.Token;
import model.token.TokenType;

public class DynamicSupplierTokenNodeFactory {
	private static final Map<TokenType,Supplier<? extends AbstractUnaryFunction>> registeredSuppliers1 = new HashMap<>();
	private static final Map<TokenType,Supplier<? extends AbstractBinaryFunction>> registeredSuppliers2 = new HashMap<>();
	static {
		registeredSuppliers2.put(TokenType.PLUS, 	  new SumSupplier());
		registeredSuppliers2.put(TokenType.MINUS, 	  new DifferenceSupplier());
		registeredSuppliers2.put(TokenType.DIVIDED_BY,new DivisionSupplier());
		registeredSuppliers2.put(TokenType.TIMES, 	  new ProductSupplier());
		registeredSuppliers2.put(TokenType.RAISED_TO, new PowerSupplier());
		registeredSuppliers1.put(TokenType.LOG, 	  new LogSupplier());
		registeredSuppliers1.put(TokenType.SINE, 	  new SinSupplier());
		registeredSuppliers1.put(TokenType.COSINE, 	  new CosSupplier());
		registeredSuppliers1.put(TokenType.TANGENT,   new TanSupplier());
	}
	public static void registerBinarySupplier(TokenType type,Supplier<? extends AbstractBinaryFunction> supplier) {
		registeredSuppliers2.put(type,supplier);
	}
	public static void registerUnarySupplier(TokenType type,Supplier<? extends AbstractUnaryFunction> supplier) {
		registeredSuppliers1.put(type,supplier);
	}
	
	public static Optional<Token> createToken(Token left,Token right,TokenType type) {
		AbstractBinaryFunction token = registeredSuppliers2.get(type).get();
		if(token == null) {
			return Optional.empty();
		}
		token.setLeft(left);
		token.setRight(right);
		return Optional.of(token); 
	}
	public static Optional<Token> createToken(Token child,TokenType type) {
		AbstractUnaryFunction token = registeredSuppliers1.get(type).get();
		if(token == null) {
			return Optional.empty();
		}
		token.setChild(child);
		return Optional.of(token);
		
	}

}
