package tests;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.interfaces.Interval;
import model.main.IntervalFactory;
import model.main.IntervalImpl;
public class createIntervalTest {
	
	@Test
	void singleInterval() {
		String text = "(5.5;345)";
		Interval[] intervals = IntervalFactory.createInterval(text);
		Interval interval = new IntervalImpl(5.5,345);
		
		assertEquals(interval,intervals[0]);
	}
	@Test
	void multipleInterval() {
		String text = "(5.5;345)(0;235.23)";
		Interval[] intervals = IntervalFactory.createInterval(text);
		Interval interval = new IntervalImpl(5.5,345);
		Interval interval1 = new IntervalImpl(0,235.23);
		assertEquals(interval,intervals[0]);
		assertEquals(interval1,intervals[1]);
	}
	@Test
	void oneRightOneWrongShouldOnlyReturnRight() {
		String text = "(5.5;345)(0;";
		Interval[] intervals = IntervalFactory.createInterval(text);
		Interval interval = new IntervalImpl(5.5,345);
		assertEquals(interval,intervals[0]);
		
	}

}
