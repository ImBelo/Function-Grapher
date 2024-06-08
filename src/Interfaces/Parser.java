package Interfaces;

import java.util.Optional;

import Prova.ExpressionImpl;
import Prova.FunctionImpl;

public interface Parser {
	public Optional<Function> parse(Expression expr);
}
