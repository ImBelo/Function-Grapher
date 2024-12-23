package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.main.*;
import model.token.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.function.Supplier;

	class TestDynamicSupplierTokenNodeFactory {

	    // Prepare the necessary test objects and mock suppliers for the tests
	    private Token leftToken, rightToken, childToken;
	    private Supplier<? extends AbstractBinaryFunction> sumSupplier;
	    private Supplier<? extends AbstractUnaryFunction> sinSupplier;

	    @BeforeEach
	    void setUp() {
	        // Create mock tokens to test with
	        leftToken = new MyNumber(2.0); // A mock token for left operand
	        rightToken = new MyNumber(3.0); // A mock token for right operand
	        childToken = new MyNumber(5.0); // A mock token for unary function
	        
	        // Create mock suppliers
	        sumSupplier = () -> new Sum(leftToken, rightToken);
	        sinSupplier = () -> new Sin(childToken);
	        
	        // Register the suppliers with the factory
	        DynamicSupplierTokenNodeFactory.registerBinarySupplier(TokenType.PLUS, sumSupplier);
	        DynamicSupplierTokenNodeFactory.registerUnarySupplier(TokenType.SINE, sinSupplier);
	    }

	    @Test
	    void testRegisterBinarySupplier() {
	        // Testing if the factory correctly registers binary suppliers
	        TokenType tokenType = TokenType.PLUS;
	        Optional<Token> token = DynamicSupplierTokenNodeFactory.createToken(leftToken, rightToken, tokenType);

	        assertTrue(token.isPresent(), "Token should be present when the binary supplier is registered.");
	        assertTrue(token.get() instanceof AbstractBinaryFunction, "The created token should be an instance of AbstractBinaryFunction.");
	        assertEquals(5.0, ((AbstractBinaryFunction) token.get()).getValue(), "The value of the binary function (2 + 3) should be 5.0.");
	    }

	    @Test
	    void testRegisterUnarySupplier() {
	        // Testing if the factory correctly registers unary suppliers
	        TokenType tokenType = TokenType.SINE;
	        Optional<Token> token = DynamicSupplierTokenNodeFactory.createToken(childToken, tokenType);

	        assertTrue(token.isPresent(), "Token should be present when the unary supplier is registered.");
	        assertTrue(token.get() instanceof AbstractUnaryFunction, "The created token should be an instance of AbstractUnaryFunction.");
	        assertEquals(Math.sin(5.0), ((AbstractUnaryFunction) token.get()).getValue(), "The value of the unary function (sin(5)) should be the correct result.");
	    }

	    @Test
	    void testCreateTokenWithInvalidType() {
	        // Testing the behavior when trying to create a token with an unregistered type
	        TokenType invalidType = TokenType.LOG; // Assuming LOG isn't registered for binary functions
	        Optional<Token> token = DynamicSupplierTokenNodeFactory.createToken(leftToken, rightToken, invalidType);

	        assertFalse(token.isPresent(), "The token creation should fail for unregistered token types.");
	    }

	    @Test
	    void testCreateBinaryFunctionWithNullLeftOperand() {
	        // Testing the behavior when a binary function is created with a null left operand
	        TokenType tokenType = TokenType.PLUS;
	        Optional<Token> token = DynamicSupplierTokenNodeFactory.createToken(null, rightToken, tokenType);

	        assertFalse(token.isPresent(), "The token creation should fail when the left operand is null.");
	    }

	    @Test
	    void testCreateUnaryFunctionWithNullChild() {
	        // Testing the behavior when a unary function is created with a null child token
	        TokenType tokenType = TokenType.SINE;
	        Optional<Token> token = DynamicSupplierTokenNodeFactory.createToken(null, tokenType);

	        assertFalse(token.isPresent(), "The token creation should fail when the child token is null.");
	    }
	}

