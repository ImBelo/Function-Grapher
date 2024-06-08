package Prova;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import Token.AbstractBinaryFunction;
import Token.AbstractToken;
import Token.AbstractUnaryFunction;
import Interfaces.Expression;
import Interfaces.TokenList;
import Interfaces.UnaryFunction;
import Token.Token;
import Token.TokenType;
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
