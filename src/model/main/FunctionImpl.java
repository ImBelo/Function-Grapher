package model.main;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Function;
import model.token.Token;
import model.token.Variable;
// class that describes the abstract syntax tree of a mathematical function
public class FunctionImpl implements Function {
	private Token root;
	private List<Variable> variables = new ArrayList<>();
	
	public FunctionImpl(Token root, Variable ...variables) {
		this.root = root;
		for(Variable variable: variables) {
			this.variables.add(variable);
		}
	}
	// evaluates the function(abstract syntax tree) 
	// in the point that has up to variables.size() dimensions
	public double evaluateAt(double ...nums) {
		for(int i = 0; i < Math.min(variables.size(),nums.length);i++) {
			variables.get(i).setNum(nums[i]);
		}
		return this.root!=null?this.root.getValue():0;
	}

}
