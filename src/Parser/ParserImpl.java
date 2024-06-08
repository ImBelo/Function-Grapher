package Parser;

import Interfaces.Function;
import Interfaces.Lexer;
import Interfaces.Parser;
import Interfaces.TokenList;
import Prova.ErrorFinderSemantic;
import Prova.ExpressionImpl;
import Prova.FunctionImpl;
import Token.NotWellFormedFormulaException;
import Token.Token;
import Token.Variable;
import Token.VariableX;
import Token.VariableY;
import factories.TokenNodeFactoryImpl;

import java.util.Optional;

import Interfaces.Expression;

public class ParserImpl implements Parser {
	private Lexer lexer = new LexerImpl();
	private TokenList tokens;
	private static Variable x = VariableX.getInstance();
	private static Variable y = VariableY.getInstance();
	public Optional<Function> parse(Expression expr) { 
		 
		// creates TokenList
		
		tokens = lexer.tokenize(expr);
		
		Token root = null;
		if(!ErrorFinderSemantic.checkSemantic(tokens)){
			throw new NotWellFormedFormulaException(null,null);
		}
		if (tokens != null) {
			// creates Abstrax Syntax Tree
			 root = TokenNodeFactoryImpl.createTokenNode(tokens).orElseThrow(NotWellFormedFormulaException::new);	 
		}
		return Optional.ofNullable(new FunctionImpl(root, x, y)); // abstract syntax tree containing the expression
    }




}