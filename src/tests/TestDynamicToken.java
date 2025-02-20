package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.main.*;
import model.token.*;

import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;
class TestDynamicToken{

	    // Prepare the necessary test objects and mock suppliers for the tests
	    private Token leftToken, rightToken, childToken;
	    private Type modulus;
	    private Type arctan;
	    @BeforeEach
	    void setUp() {
	        // Create mock tokens to test with
	        leftToken = new MyNumber(10.0); // A mock token for left operand
	        rightToken = new MyNumber(3.0); // A mock token for right operand
	        childToken = new MyNumber(5.0); // A mock token for unary function
	        
	        // Create mock suppliers
	        
	        // Register the suppliers with the factory
	       modulus = DynamicTokenType.createTokenType("%", ()->new Division() {
	        	@Override
	        	public double getValue() {
	        		return (double)((int)realValue(getLeft())%(int)realValue(getRight()));
	        	}
	        
	        }
	        
	        		
	        		
	        		);
	        arctan = DynamicTokenType.createTokenType("arctan", ()->new Tan() {
	        	@Override
	        	public double getValue() {
	        		return Math.atan(realValue(getChild()));
	        	}
	        });
	    }

	    @Test
	    void testRegisterBinarySupplier() {
	        // Testing if the factory correctly registers binary suppliers
	        Optional<Token> token = TokenNodeFactory.createToken(leftToken, rightToken, modulus);

	        assertTrue(token.isPresent(), "Token should be present when the binary supplier is registered.");
	        assertTrue(token.get() instanceof BinaryFunction, "The created token should be an instance of AbstractBinaryFunction.");
	        assertEquals(1.0, token.get().getValue(), "The value of the binary function (10 % 3) should be 1.0");
	    }

	    @Test
	    void testRegisterUnarySupplier() {
	        // Testing if the factory correctly registers unary suppliers
	        Optional<Token> token = TokenNodeFactory.createToken(childToken, arctan);

	        assertTrue(token.isPresent(), "Token should be present when the unary supplier is registered.");
	        assertTrue(token.get() instanceof UnaryFunction, "The created token should be an instance of AbstractUnaryFunction.");
	        assertEquals(Math.atan(5.0),  token.get().getValue(), "The value of the unary function (atan(5)) should be the correct result.");
	    }

	    @Test
	    void testCreateTokenWithInvalidType() {
	        // Testing the behavior when trying to create a token with an unregistered type
	        Type invalidType = FunctionType.LOG; // Assuming LOG isn't registered for binary functions
	        Optional<Token> token = TokenNodeFactory.createToken(leftToken, rightToken, invalidType);

	        assertFalse(token.isPresent(), "The token creation should fail for unregistered token types.");
	    }

	    @Test
	    void testCreateBinaryFunctionWithNullLeftOperand() {
	        // Testing the behavior when a binary function is created with a null left operand
	        Type tokenType = OperationType.PLUS;
	        Optional<Token> token = TokenNodeFactory.createToken(null, rightToken, tokenType);

	        assertFalse(token.isPresent(), "The token creation should fail when the left operand is null.");
	    }

	    @Test
	    void testCreateUnaryFunctionWithNullChild() {
	        // Testing the behavior when a unary function is created with a null child token
	        Type tokenType = FunctionType.SINE;
	        Optional<Token> token = TokenNodeFactory.createToken(null, tokenType);

	        assertFalse(token.isPresent(), "The token creation should fail when the child token is null.");
	    }
}