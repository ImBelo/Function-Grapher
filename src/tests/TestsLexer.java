package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.token.*;
import model.main.*;
import model.parser.*;
import model.interfaces.*;
class TestsLexer {
	Lexer lexer;
	@BeforeEach
	void init(){
		this.lexer = new LexerImpl(new TokenFactoryImpl(TokenType.getTokens()));
	}
	@Test
	void test() {
		Expression expr = new ExpressionImpl("2+x+cos(x+3.34)^4");
		lexer.tokenize(expr);
		TokenList expected = new TokenListImpl();
		expected.addToken(new MyNumber(2.0));
		expected.addToken(new Sum());
		expected.addToken(new VariableX());
		expected.addToken(new Sum());
		expected.addToken(new Cos());
		expected.addToken(new OpenParentheses());
		expected.addToken(new VariableX());
		expected.addToken(new Sum());
		expected.addToken(new MyNumber(3.34));
		expected.addToken(new ClosedParentheses());
		expected.addToken(new Pow());
		expected.addToken(new MyNumber(4.0));
		
		TokenList actual = lexer.tokenize(expr);
		assertEquals(expected,actual);
	}
	@Test
	void ParenthesesAssertThrowSyntaxError() {
		 assertThrows(NotWellFormedFormulaException.class,
		            ()->{
		        		Expression expr = new ExpressionImpl("(()");
		        		lexer.tokenize(expr);
		            
		            });
		 assertThrows(NotWellFormedFormulaException.class,
		            ()->{
		        		Expression expr = new ExpressionImpl("(())(()");
		        		lexer.tokenize(expr);
		            
		            });
	}
	@Test
	void FunctionSyntaxErrorShouldThrow() {
		 assertThrows(NotWellFormedFormulaException.class,
		            ()->{
		        		Expression expr = new ExpressionImpl("siin(x)");
		        		lexer.tokenize(expr);
		            
		            });
		 assertThrows(NotWellFormedFormulaException.class,
		            ()->{
		        		Expression expr = new ExpressionImpl("sdag289432jdf38");
		        		lexer.tokenize(expr);
		            
		            });
	}
	
}


