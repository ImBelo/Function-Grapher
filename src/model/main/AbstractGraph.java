package model.main;
import java.util.List;
import java.util.Optional;

import model.interfaces.*;
import model.parser.ParserImpl;

public abstract class AbstractGraph implements Graph{
	protected List<Point> points;
	protected Expression expr; 
	protected Function function; // syntax abstract tree
	protected Parser parser;
	public AbstractGraph() {
		
	}
	public AbstractGraph(Expression expr) {
		parser = new ParserImpl();
		this.expr = expr;
		function = parser.parse(expr);
	}
	public int size() {
		return this.points!=null?this.points.size():0;
	}
	public abstract void updateGraph();
	public void setExpression(Expression expr) {
		this.expr = expr;
		Parser parser = new ParserImpl();
		this.function = parser.parse(expr);
	}
	public Expression getExpression() {
		return this.expr;
	}
	public abstract Point getPoint(int index);
	public abstract Point getNextPoint(Point p);
	public List<Point> getPoints(){
		return this.points;
	}	
	

	

}
