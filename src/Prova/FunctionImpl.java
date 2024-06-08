package Prova;

import java.util.ArrayList;
import java.util.LinkedList;

import Interfaces.Function;
import Token.Token;
import Token.Variable;
import Token.VariableX;
import Token.VariableY;

public class FunctionImpl implements Function {
	Token root;
	private ArrayList<Variable> variables = new ArrayList<>();
	
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
