package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.token.*;
import model.main.*;
import model.parser.*;
import model.interfaces.*;
class TestsLexer {

	@Test
	void test() {
		Lexer lexer = new LexerImpl();
		Expression expr = new ExpressionImpl("2+x+cos(x)");
		lexer.tokenize(expr);
		TokenList expected = new TokenListImpl();
		expected.addToken(new MyNumber(2.0));
		expected.addToken(new Sum(null,null));
		expected.addToken(new VariableX());
		expected.addToken(new Sum(null,null));
		expected.addToken(new Cos(null));
		expected.addToken(new OpenParentheses());
		expected.addToken(new VariableX());
		expected.addToken(new ClosedParentheses());
		TokenList actual = lexer.tokenize(expr);
		assertEquals(expected,actual);
	}
	@Test
	void ParenthesesAssertThrowSyntaxError() {
		 assertThrows(NotWellFormedFormulaException.class,
		            ()->{
		            	Lexer lexer = new LexerImpl();
		        		Expression expr = new ExpressionImpl("(()");
		        		lexer.tokenize(expr);
		            
		            });
		 assertThrows(NotWellFormedFormulaException.class,
		            ()->{
		            	Lexer lexer = new LexerImpl();
		        		Expression expr = new ExpressionImpl("(())(()");
		        		lexer.tokenize(expr);
		            
		            });
	}
	@Test
	void FunctionSyntaxErrorShouldThrow() {
		 assertThrows(NotWellFormedFormulaException.class,
		            ()->{
		            	Lexer lexer = new LexerImpl();
		        		Expression expr = new ExpressionImpl("siin(x)");
		        		lexer.tokenize(expr);
		            
		            });
		 assertThrows(NotWellFormedFormulaException.class,
		            ()->{
		            	Lexer lexer = new LexerImpl();
		        		Expression expr = new ExpressionImpl("sdag289432jdf38");
		        		lexer.tokenize(expr);
		            
		            });
	}
	
}


