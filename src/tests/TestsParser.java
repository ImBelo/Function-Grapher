package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.interfaces.Expression;
import model.interfaces.Function;
import model.interfaces.Parser;
import model.main.ExpressionImpl;
import model.parser.ParserImpl;
import model.token.NotWellFormedFormulaException;

class TestsParser {
		@Test
		void operationsPriorityCheck() {
			Parser parser = new ParserImpl();
			Expression expr = new ExpressionImpl("2+2*2+2^3");
			Function f = parser.parse(expr).orElseThrow(NotWellFormedFormulaException::new);
			// 2+2*2+2^3
			// 2+2*2+8
			// 2+4+8
			// 14
			assertEquals(14 , f.evaluateAt(0));
		}
		@Test
		void operationsPriorityCheckParentheses() {
			Parser parser = new ParserImpl();
			Expression expr = new ExpressionImpl("(2+2*2+2)^3");
			Function f = parser.parse(expr).orElseThrow(NotWellFormedFormulaException::new);
			// (2+2*2+2)^3
			// (2+4+2)^3
			// 8^3
			// 512
			assertEquals(512 , f.evaluateAt());
		}
		@Test
		void logCheck() {
			Parser parser = new ParserImpl();
			Expression expr = new ExpressionImpl("2+log(5)");
			Function f = parser.parse(expr).orElseThrow(NotWellFormedFormulaException::new);
		
			assertEquals(2+Math.log(5.0) , f.evaluateAt(0),1E-3);
		}
		@Test
		void sinCheck() {
			Parser parser = new ParserImpl();
			Expression expr = new ExpressionImpl("sin(6.3)*4");
			Function f = parser.parse(expr).orElseThrow(NotWellFormedFormulaException::new);
		
			assertEquals(Math.sin(6.3)*4 , f.evaluateAt(0),1E-3);
		}
		@Test
		void cosCheck() {
			Parser parser = new ParserImpl();
			Expression expr = new ExpressionImpl("cos(3+2/3)");
			Function f = parser.parse(expr).orElseThrow(NotWellFormedFormulaException::new);
		
			assertEquals(Math.cos(3+2/3.0) , f.evaluateAt(0),1E-3);
		}
		
		
	

}
