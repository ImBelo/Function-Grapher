package model.main;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import model.factories.NumberFactory;
import model.interfaces.Expression;
import model.interfaces.TokenFactory;
import model.token.Token;
import model.token.TokenType;
import model.token.Type;

public class TokenFactoryImpl implements TokenFactory{
	List<Type> types;
	public TokenFactoryImpl(List<Type> types) {
		this.types=types;	
	}
	public Optional<Token> extractToken(Expression expr) {
		Character currentChar = expr.getExpression().charAt(0);
		
		if (Character.isDigit(currentChar)) 
			return NumberFactory.createNumber(expr);
		
		for(Type type: types){
			String name = type.getData(); 
			if(expr.getExpression().startsWith(name)) {
				expr.setExpression(expr.getExpression().substring(name.length()));
				Token token = type.getSupplier().get();
				return Optional.ofNullable(token);
			}
			
		}
			
		return Optional.empty();
		
	}
	@Override
	public void setDictionary(Type... types) {
		this.types = Arrays.asList(types);
	}
	@Override
	public List<Type> getDictionary() {
		return this.types;
	}
	

}
