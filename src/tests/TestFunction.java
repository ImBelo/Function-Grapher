package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.main.ExpressionImpl;
import model.main.TokenFactoryImpl;
import model.parser.LexerImpl;
import model.parser.ParserImpl;
import model.token.TokenType;
import model.interfaces.*;
import model.*;
public class TestFunction {
	private Parser parser;
	@BeforeEach
	void init() {
		parser = new ParserImpl(new LexerImpl(new TokenFactoryImpl(TokenType.getTokens())));
	}
	@Test
	void TestVariables() {
		
		Expression expr = new ExpressionImpl("x+3-sin(x)+y-log(y)");
		Function f = parser.parse(expr);
		assertEquals(3+3-Math.sin(3)+2-Math.log(2), f.evaluateAt(3,2));
	}
	@Test
	void TestVariables2() {
		
		Expression expr = new ExpressionImpl("x+3-sin(x)+y-log(y)");
		Function f = parser.parse(expr);
		assertEquals(3+3-Math.sin(3)+0-Math.log(0), f.evaluateAt(3,0));
	}
}
