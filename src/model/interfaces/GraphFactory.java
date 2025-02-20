package model.interfaces;

import java.util.Optional;


public interface GraphFactory {
	public Optional<Graph> createGraph(Expression expr,Parser parser);
	public Optional<Graph> createGraph(Expression expr);
}
