package model.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.factories.NumberFactory;
import model.interfaces.Interval;
import model.token.MyNumber;
import model.token.NotWellFormedFormulaException;

public class IntervalFactory {
	public static Interval[] createInterval(String text) {
		List<Interval> intervals = new LinkedList<>();
		String regex = "\\(([-?0-9?.]+);([-?0-9?.]+)\\)*";
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(text);
	   	
	   
	   
	    while(m.find()) {
	    	String num1 = m.group(1);
	    	String num2 = m.group(2);
	    	double leftBound = Double.parseDouble(num1);
	    	double rightBound = Double.parseDouble(num2);
	    	intervals.add(new IntervalImpl(leftBound,rightBound));
	    }
	    Interval[] interval = new Interval[intervals.size()];
	    return intervals.toArray(interval);
		
		
		
		
	}
}
