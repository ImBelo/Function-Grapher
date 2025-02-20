package model.main;
import model.parser.*;
import model .interfaces.*;
import controller.Controller;
import controller.ControllerImpl;
import model.parser.LexerImpl;
import model.parser.ParserImpl;
import model.token.TokenType;
import model.token.Type;
import model.token.UnaryFunction;
import view.View;
import view.ViewImpl;

import java.util.Set;

import controller.*;
public class FunctionGrapher{
	public static void main(String[] args) {
		 Type arctan = DynamicTokenType.createTokenType("htan", ()->new UnaryFunction("htan") {
	        	@Override
	        	public double getValue() {
	        		return Math.tanh(realValue(getChild()));
	        	}
	        });
	
		
		
		ModelImpl model = new ModelImpl();
		View view = new ViewImpl();
		Controller controller = ControllerImpl.init(model,view);
		view.setController(controller);
		controller.start();
		
		
	}
}