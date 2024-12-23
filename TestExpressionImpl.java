package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.main.*;
public class TestExpressionImpl {

	    @Test
	    public void testCheckArityWithBinaryExpression() {
	        // Test case: Expression containing 'y' should return Binary arity
	        assertEquals(Arity.Binary, ExpressionImpl.checkArity("2y"), "Expression '2y' should return Binary arity.");
	        assertEquals(Arity.Binary, ExpressionImpl.checkArity("xy"), "Expression 'xy' should return Binary arity.");
	        assertEquals(Arity.Binary, ExpressionImpl.checkArity("y3"), "Expression 'y3' should return Binary arity.");
	        assertEquals(Arity.Binary, ExpressionImpl.checkArity("y"), "Expression 'y' alone should return Binary arity.");
	        assertEquals(Arity.Binary, ExpressionImpl.checkArity(" y "), "Expression ' y ' should return Binary arity.");
	    }

	    @Test
	    public void testCheckArityWithUnaryExpression() {
	        // Test case: Expression without 'y' should return Unary arity
	        assertEquals(Arity.Unary, ExpressionImpl.checkArity("2x"), "Expression '2x' should return Unary arity.");
	        assertEquals(Arity.Unary, ExpressionImpl.checkArity("abc"), "Expression 'abc' should return Unary arity.");
	        assertEquals(Arity.Unary, ExpressionImpl.checkArity("x + 5"), "Expression 'x + 5' should return Unary arity.");
	    }

	    @Test
	    public void testExpressionImplConstructor() {
	        // Test case: Constructor correctly assigns expression and arity
	        ExpressionImpl expr = new ExpressionImpl("x + y");
	        assertEquals("x + y", expr.getExpression(), "Constructor should correctly set the expression.");
	        assertEquals(Arity.Binary, expr.getArity(), "Constructor should correctly set Binary arity.");

	        expr = new ExpressionImpl("x + 5");
	        assertEquals("x + 5", expr.getExpression(), "Constructor should correctly set the expression.");
	        assertEquals(Arity.Unary, expr.getArity(), "Constructor should correctly set Unary arity.");
	    }

	    @Test
	    public void testSetExpression() {
	        // Test case: Setting expression updates both the expression and its arity
	        ExpressionImpl expr = new ExpressionImpl("x + y");
	        expr.setExpression("2x");
	        assertEquals("2x", expr.getExpression(), "setExpression should correctly update the expression.");
	        assertEquals(Arity.Unary, expr.getArity(), "setExpression should correctly update the arity.");
	    }

	    @Test
	    public void testGetExpression() {
	        // Test case: getExpression correctly returns the current expression
	        ExpressionImpl expr = new ExpressionImpl("x + 5");
	        assertEquals("x + 5", expr.getExpression(), "getExpression should return the correct expression.");
	        
	        expr.setExpression(null);
	        assertEquals(null, expr.getExpression(), "getExpression should return null for a null expression.");
	    }
	}
