package model.main;
import java.util.List;
import java.util.Optional;

import model.interfaces.*;
import model.parser.ParserImpl;

public abstract class AbstractGraph implements Graph{
	protected List<PointImpl> points;
	protected Expression expr; 
	protected Function function; // syntax abstract tree
	protected Parser parser;
	public AbstractGraph(Expression expr) {
		parser = new ParserImpl();
		this.expr = expr;
		function = parser.parse(expr);
	}
	public int size() {
		return this.points!=null?this.points.size():0;
	}
	public abstract void updateGraph();
	public abstract PointImpl getPoint(int index);
	public abstract PointImpl getNextPoint(PointImpl p);
	public List<PointImpl> getPoints(){
		return this.points;
	}	
	

	

}
