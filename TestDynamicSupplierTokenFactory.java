package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import model.main.*;
import model.token.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDynamicSupplierTokenFactory {

    private ExpressionImpl expression;

    @BeforeEach
    void setUp() {
        // Initialize a real expression for testing
        expression = new ExpressionImpl("123+456");
    }

    @Test
    void testExtractNumberToken() {
        // Extract the first token (a number)
        Optional<Token> token = DynamicSupplierTokenFactory.extractToken(expression);

        // Assertions
        assertTrue(token.isPresent(), "Token should be extracted successfully");
        assertTrue(token.get() instanceof MyNumber, "Token should be of type MyNumber");

        MyNumber number = (MyNumber) token.get();
        assertEquals(123, number.getValue(), "The extracted number should be 123");
    }

    @Test
    void testExtractOperationToken() {
        // Update the expression to start with an operation
        expression.setExpression("+456");

        // Extract the operation token
        Optional<Token> token = DynamicSupplierTokenFactory.extractToken(expression);

        // Assertions
        assertTrue(token.isPresent(), "Token should be extracted successfully");
        assertTrue(TokenType.getOperations().contains(token.get().getType()), "Token should be of type OPERATION");
        assertEquals("+", token.get().toString(), "The extracted operation should be '+'");
    }

    @Test
    void testExtractInvalidToken() {
        // Set an invalid expression
        expression.setExpression("@");

        // Attempt to extract a token
        Optional<Token> token = DynamicSupplierTokenFactory.extractToken(expression);

        // Assertions
        assertFalse(token.isPresent(), "No token should be extracted from an invalid expression");
    }

    @Test
    void testComplexExpression() {
        // Set a complex expression
        expression.setExpression("3.14*sin(x)+cos(y)^2");

        // Extract tokens iteratively
        while (!expression.getExpression().isEmpty()) {
            Optional<Token> token = DynamicSupplierTokenFactory.extractToken(expression);
            assertTrue(token.isPresent(), "Each token should be extracted successfully");

            // Verify token types dynamically
            Token extractedToken = token.get();
            if (TokenType.getOperations().contains(extractedToken.getType())) {
                assertTrue(extractedToken.toString().matches("[+\\-*/^]"), "Operation token should have a valid operator representation");
            } else if (TokenType.getFunctions().contains(extractedToken.getType())) {
                assertTrue(extractedToken.toString().matches("sin|cos|tan|log"), "Function token should have a valid representation");
            } else if (TokenType.getVariables().contains(extractedToken.getType())) {
                assertTrue(extractedToken.toString().matches("x|y"), "Variable token should have a valid representation");
            } else if (extractedToken.getType() == TokenType.NUMBER) {
                assertTrue(extractedToken instanceof MyNumber, "Number token should be of type MyNumber");
            } else {
                fail("Unexpected token type: " + extractedToken.getType());
            }
        }
    }
}


