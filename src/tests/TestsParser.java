package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.interfaces.Expression;
import model.interfaces.Function;
import model.interfaces.Parser;
import model.main.ExpressionImpl;
import model.main.FunctionImpl;
import model.main.TokenFactoryImpl;
import model.parser.LexerImpl;
import model.parser.ParserImpl;
import model.token.MyNumber;
import model.token.NotWellFormedFormulaException;
import model.token.Sum;
import model.token.Token;
import model.token.TokenType;
class TestsParser {
		Parser parser;
		@BeforeEach
		void init() {
			this.parser = new ParserImpl(new LexerImpl(new TokenFactoryImpl(TokenType.getTokens())));
		}
		
		@Test
		void operationsPriorityCheck() {
			Expression expr = new ExpressionImpl("2+2*2+2^3");
			Function f = parser.parse(expr);
			// 2+2*2+2^3
			// 2+2*2+8
			// 2+4+8
			// 14
			assertEquals(14 , f.evaluateAt());
		}
		@Test
		void operationsPriorityCheckParentheses() {
			Expression expr = new ExpressionImpl("(2+2*2+2)^3");
			Function f = parser.parse(expr);
			// (2+2*2+2)^3
			// (2+4+2)^3
			// 8^3
			// 512
			assertEquals(512 , f.evaluateAt());
		}
		@Test
		void logCheck() {
			Expression expr = new ExpressionImpl("2+log(5)");
			Function f = parser.parse(expr);
		
			assertEquals(2+Math.log(5.0) , f.evaluateAt(0),1E-3);
		}
		@Test
		void sinCheck() {
			Expression expr = new ExpressionImpl("sin(6.3)*4");
			Function f = parser.parse(expr);
		
			assertEquals(Math.sin(6.3)*4 , f.evaluateAt(0),1E-3);
		}
		@Test
		void cosCheck() {
			Expression expr = new ExpressionImpl("cos(3+2/3)");
			Function f = parser.parse(expr);
		
			assertEquals(Math.cos(3+2/3.0) , f.evaluateAt(0),1E-3);
		}
		@Test
		void NullNodeShouldThrowException() {
			 assertThrows(NotWellFormedFormulaException.class,
					 ()->{//abstract syntax tree
						  //   +
						  //  / \
						  //null 3
						 Token root = new Sum(null,new MyNumber(3));
						 Function f = new FunctionImpl(root,TokenType.getVariables()) ;
						 f.evaluateAt();
					 }
					 
					 );
		}
}
		
	

