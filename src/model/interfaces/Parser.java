package model.interfaces;

import java.util.Optional;


public interface Parser {
	public Optional<Function> parse(Expression expr);
}
