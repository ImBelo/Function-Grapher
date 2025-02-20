package model.main;

import java.util.List;
import java.util.Optional;

import model.token.BinaryFunction;
import model.token.NotWellFormedFormulaException;
import model.token.Token;
import model.token.TokenType;
import model.token.Type;
import model.token.UnaryFunction;

public class TokenNodeFactory {
	
	public static Optional<Token> createToken(Token left,Token right,Type type) {
		List<Type> operations = TokenType.getOperations();
		if(!operations.contains(type) || right == null || left == null)
			return Optional.empty();
	
		BinaryFunction token = (BinaryFunction)type.getSupplier().get();
		token.setLeft(left);
		token.setRight(right);
		return Optional.of(token); 
	}
	public static Optional<Token> createToken(Token child,Type type) {
		List<Type> functions = TokenType.getFunctions();
		if(!functions.contains(type) || child == null) {
			return Optional.empty();
		}
		UnaryFunction token = (UnaryFunction)type.getSupplier().get();
		token.setChild(child);
		return Optional.of(token);
		
	}

}
