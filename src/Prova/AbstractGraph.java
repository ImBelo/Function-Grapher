package Prova;
import java.util.List;

import Interfaces.Camera;
import Interfaces.Expression;
import Interfaces.Function;
import Interfaces.Parser;
import Parser.ParserImpl;

public abstract class AbstractGraph implements Graph{
	protected List<PointImpl> points;
	protected Expression expr; 
	protected Function function; // syntax abstract tree
	protected Parser parser;
	public AbstractGraph(Expression expr) {
		parser = new ParserImpl();
		this.expr = expr;
		if(parser.parse(expr).isPresent())
			function = parser.parse(expr).get();
		
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
