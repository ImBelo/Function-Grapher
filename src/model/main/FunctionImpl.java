package model.main;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Function;
import model.token.Token;
import model.token.Variable;

public class FunctionImpl implements Function {
	private Token root;
	private List<Variable> variables = new ArrayList<>();
	
	public FunctionImpl(Token root, Variable ...variables) {
		this.root = root;
		for(Variable variable: variables) {
			this.variables.add(variable);
		}
	}
	
	public double evaluateAt(double ...nums) {
		for(int i = 0; i < variables.size() && i < nums.length;i++) {
			variables.get(i).setNum(nums[i]);
		}
		return this.root!=null?this.root.getValue():0;
	}

}
