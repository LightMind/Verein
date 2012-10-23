package test.lesson;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import plan.Lesson;
import util.LessonTime;

public class LessonTimeTest {
	List<Lesson> list = new ArrayList<Lesson>();
	@Before
	public void setUp() throws Exception {
		Lesson l1 = new Lesson(1);
		Calendar c1 = new GregorianCalendar();
		c1.clear();
		c1.set(2012, 1, 15, 12, 0);
		Calendar ce1 = new GregorianCalendar();
		ce1.clear();
		ce1.set(2012, 1, 15, 13, 0);
		l1.setBeginn(c1);
		l1.setEnd(ce1);;
		list.add(l1);
		
		Lesson l2 = new Lesson(2);
		Calendar start2 = new GregorianCalendar();
		start2.clear();
		start2.set(2012, 1, 16, 12, 0);
		Calendar end2 = new GregorianCalendar(2012, 1, 16, 13, 0);
		l2.setBeginn(start2);
		l2.setEnd(end2);
		list.add(l2);
		
		Lesson l3 = new Lesson(3);
		Calendar start3 =new GregorianCalendar(2012,1,16,13,0);
		l3.setBeginn(start3);
		l3.setEnd(new GregorianCalendar(2012, 1, 16, 14, 0));
		list.add(l3);
		
		Lesson l4 = new Lesson(4);
		Calendar start4 = new GregorianCalendar(2012, 1, 17, 13, 0); 
		l4.setBeginn(start4);
		l4.setEnd(new GregorianCalendar(2012, 1, 17, 14, 0));
		list.add(l4);		
		
		
		Collections.sort(list);
	}

	@Test
	public void testBefore() {
		List<Lesson> result = LessonTime.before(list, new GregorianCalendar(2012, 1, 17, 13, 0));
		assertTrue(result.size() == 3);
	}

	@Test
	public void testAfter() {
		List<Lesson> result = LessonTime.after(list, new GregorianCalendar(2012, 1, 17, 13, 0));
		assertTrue(result.size() == 1);
	}

	@Test
	public void testBetween() {
		List<Lesson> result = LessonTime.between(list, new GregorianCalendar(2012, 1, 15, 14, 0), new GregorianCalendar(2012,1,17,12,0));
		assertTrue(result.size() == 2);
	}

}
