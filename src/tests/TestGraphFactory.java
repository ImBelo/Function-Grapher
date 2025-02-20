package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import model.main.*;
import model.token.*;
import model.interfaces.*;
import model.factories.*;

	public class TestGraphFactory {

	    @Test
	    void testCreateGraphWithUnaryExpression() {
	        // Create an instance of Expression with Unary arity
	        Expression unaryExpression = new ExpressionImpl("sin(x)"); // Example of a unary function
	        GraphFactory gf = new GraphFactoryImpl();
	        // Use GraphFactory to create the graph
	        Graph graph = gf.createGraph(unaryExpression).orElseThrow(NotWellFormedFormulaException::new);
	        // Verify that the created graph is an instance of GraphSingleVar
	        assertTrue(graph instanceof GraphSingleVar, "Expected GraphSingleVar for Unary Expression");

	        // Verify that the expression is correctly assigned to the graph
	        assertEquals(unaryExpression, graph.getExpression());
	    }

	    @Test
	    void testCreateGraphWithBinaryExpression() {
	        // Create an instance of Expression with Binary arity
	        Expression binaryExpression = new ExpressionImpl("x + y"); // Example of a binary function
	        GraphFactory gf = new GraphFactoryImpl();
	        // Use GraphFactory to create the graph
	        Graph graph = gf.createGraph(binaryExpression).orElseThrow(NotWellFormedFormulaException::new);

	        // Verify that the created graph is an instance of GraphTwoVar
	        assertTrue(graph instanceof GraphTwoVar, "Expected GraphTwoVar for Binary Expression");

	        // Verify that the expression is correctly assigned to the graph
	        assertEquals(binaryExpression, graph.getExpression());
	    }
	}