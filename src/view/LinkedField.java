package view;
import view.*;

public class LinkedField<T1 extends Field,T2 extends Field> {
	T1 first;
	T2 second;
	public LinkedField(T1 t1, T2 t2) {
		this.first = t1;
		this.second = t2;
		first.linkField(second);
		second.linkField(first);
	}
	public String getInterval() {
		if(second instanceof IntervalField) 
			return ((IntervalField)second).getText();
		return "";
	}
	public String getFunction() {
		if(first instanceof FunctionField) 
			return ((FunctionField)first).getText();
		return "";
	}
	public Field getFirst() {
		return this.first;
	}
	public Field getSecond() {
		return this.second;
	}

}
