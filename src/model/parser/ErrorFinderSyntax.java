package model.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.interfaces.Expression;
import model.token.NotWellFormedFormulaException;
import model.token.TokenType;

public class ErrorFinderSyntax {
public static boolean checkExpression(Expression exprLeft) {
		removeSpaces(exprLeft);
		return  checkFunctions(exprLeft) && checkParentheses(exprLeft) && checkIllegalChar(exprLeft) && checkNumber(exprLeft); 
	}
public static void removeSpaces(Expression expr) {
	if(expr == null || expr.getExpression() == null) {
		throw new NotWellFormedFormulaException(null,null);
	}
	String content = expr.getExpression();
	Pattern pattern = Pattern.compile(" ");
    Matcher matcher = pattern.matcher(content);
    
    if (matcher.find()) {
    	content = content.replaceAll(" ", "");
    	expr.setExpression(content);  	
    }
   	  	
}
public static boolean checkFunctions(Expression expr) {
	if(expr == null) {
		throw new NotWellFormedFormulaException(null,null);
	}
	String content = expr.getExpression();
	Pattern pattern = Pattern.compile("[a-wA-W]+");
    Matcher matcher = pattern.matcher(content);
    boolean exist = true;
    while (matcher.find())
    {	
    	String name= "";
    	exist = false;
    	for(int i=0;i<TokenType.getFunctions().size();i++) {
    		name = matcher.group();
    		if(name.toLowerCase().equals(TokenType.getFunctions().get(i).getData().toLowerCase())){
    			exist = true;
    		}
    	}
    	if (!exist) throw new NotWellFormedFormulaException("wrong function",null);  
    	}
	return exist;	
}
public static boolean checkParentheses(Expression expr) {
	String content = expr.getExpression();
	int counter = 0;
	for (int i =0;i < content.length();i++) {
		if(content.charAt(i) == '(')
			counter++;
		if (content.charAt(i) == ')')
			counter--;
		
	}
	if(counter != 0) {
		throw new NotWellFormedFormulaException("wrong parenthesis",null);
	}
	return true;
	
}
public static boolean checkIllegalChar(Expression expr) {
	if(expr == null ) {
		throw new NotWellFormedFormulaException(null,null);
	}
	String content = expr.getExpression();
	// not allowed characters
	Pattern pattern = Pattern.compile("[^A-Za-z0-9|+|\\-|^|/|*\\(|)|.]");
    Matcher matcher = pattern.matcher(content);   
    if (matcher.find()){
    	throw new NotWellFormedFormulaException("Symbol not in alphabet",null);
    }
    return true;
}
public static boolean checkNumber(Expression expr) {
	if(expr == null) {
		throw new NotWellFormedFormulaException(null,null);
	}
	String content = expr.getExpression();
	int comma = 0;
	for (int i = 0; i < content.length();i++) {	
		boolean isDigit = Character.isDigit(content.charAt(i));
		boolean isComma = content.charAt(i) == '.';
		comma = 0;
		if(isDigit) {
			while(isDigit | isComma && i < content.length() ) {
				isDigit = Character.isDigit(content.charAt(i));
				isComma = content.charAt(i) == '.';
				if(isComma) {
					comma++;
				}
				i++;
			}
		}
		
		if(comma!=0 && comma != 1) 
			throw new NotWellFormedFormulaException("Number not formed correctly",null);
	}
	return true;
}



}
