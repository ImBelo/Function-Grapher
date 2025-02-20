package model.factories;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import model.interfaces.Expression;
import model.interfaces.Graph;
import model.interfaces.GraphFactory;
import model.interfaces.Parser;
import model.main.Arity;
import model.main.GraphSingleVar;
import model.main.GraphTwoVar;
import model.main.TokenFactoryImpl;
import model.parser.LexerImpl;
import model.parser.ParserImpl;
import model.token.TokenType;

public class GraphFactoryImpl implements GraphFactory{
	private Map<Arity,BiFunction<Expression,Parser,Graph>> graphSuppliers;
	// default parser
	private Parser parser;
	public GraphFactoryImpl() {
		parser = new ParserImpl(new LexerImpl(new TokenFactoryImpl(TokenType.getTokens())));
		graphSuppliers = new HashMap<>();
		graphSuppliers.put(Arity.Unary,  (expr,parser)->new GraphSingleVar(expr,this.parser));
		graphSuppliers.put(Arity.Binary, (expr,parser)->new GraphTwoVar(expr,this.parser));
	}
	public void putGraph(Arity arity, BiFunction<Expression,Parser,Graph> supplier) {
		graphSuppliers.put(arity,supplier);
	}
	//using default parser
	public Optional<Graph> createGraph(Expression expr) {
		Arity arity = expr.getArity();
		Optional<Graph> graph = Optional.ofNullable(graphSuppliers.get(arity).apply(expr,this.parser));
		if(graph.isPresent())
			return graph;
		return Optional.empty();
	}
	//using given parser
	public Optional<Graph> createGraph(Expression expr,Parser parser) {
		Arity arity = expr.getArity();
		Optional<Graph> graph = Optional.ofNullable(graphSuppliers.get(arity).apply(expr,parser));
		if(graph.isPresent())
			return graph;
		return Optional.empty();
	}
}
