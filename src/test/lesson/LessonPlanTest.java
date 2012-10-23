package test.lesson;


import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import plan.Lesson;

public class LessonPlanTest {
	
	private plan.LessonPlan plan;

	@Before
	public void setUp() throws Exception {
		plan = new plan.LessonPlan();
	}
	
	@Test
	public void testSameBeginn() {
		Lesson l1 = new Lesson(1);
		l1.setBeginn(new GregorianCalendar(2012, 1, 1, 12, 0));
		Lesson l2 = new Lesson(2);
		l2.setBeginn(new GregorianCalendar(2012, 1, 1, 12, 0));
		this.plan.addLesson(l1);
		this.plan.addLesson(l2);
		System.out.println(plan.lessons);
		assertTrue(plan.lessons.size() == 2);
	}

}
