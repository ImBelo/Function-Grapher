package factories;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Interfaces.Expression;
import Prova.ExpressionImpl;
import Token.NotWellFormedFormulaException;
import Token.MyNumber;
import Token.Token;

public class NumberFactoryImpl {

	public static Optional<Token> createNumber(Expression exprLeft) {
		String content = exprLeft.getExpression();
		Pattern pattern = Pattern.compile("^([0-9?.]+)");
	    Matcher matcher = pattern.matcher(content);
	    String name="";
	    int index;
	    
	    if (matcher.find()) {
	    	name = matcher.group();
	    	index = matcher.end();
	    	content = content.substring(index);
	    	exprLeft.setExpression(content);
	    	if(content == ".")
	    		throw new NotWellFormedFormulaException("Number not well formed",null);
	    	return Optional.ofNullable(new MyNumber(Double.parseDouble(name)));	
	    }
	    
		return null;	
	}

}
