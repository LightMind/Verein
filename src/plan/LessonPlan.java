package plan;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NavigableSet;

import util.SortedObservingSet;

public class LessonPlan {
	public NavigableSet<Lesson> lessons = new SortedObservingSet<Lesson>();

	public void addLesson(Lesson newLesson) {
		lessons.add(newLesson);
	}

	public void removeLesson(Lesson lesson) {
		lessons.remove(lesson);
	}

	public NavigableSet<Lesson> getLessonsOnDay(Calendar day) {
		int year = day.get(Calendar.YEAR);
		int dayOfYear = day.get(Calendar.DAY_OF_YEAR);
		Calendar start = new GregorianCalendar(year, 0, 1, 0, 0);
		start.set(Calendar.DAY_OF_YEAR, dayOfYear);
		Calendar end = new GregorianCalendar(year, 0, 1, 0, 0);
		end.set(Calendar.DAY_OF_YEAR, dayOfYear + 1);

		Lesson lStart = new Lesson(-1);
		lStart.setBeginn(start);
		Lesson lEnd = new Lesson(-1);
		lEnd.setBeginn(end);

		return lessons.subSet(lStart, true, lEnd, false);

	}

	public NavigableSet<Lesson> getLessonsOnWeek(Calendar inWeek) {
		int year = inWeek.get(Calendar.YEAR);
		int week = inWeek.get(Calendar.WEEK_OF_YEAR);
		Calendar start = new GregorianCalendar(year, 0, 1, 0, 0);
		start.add(Calendar.WEEK_OF_YEAR, week);
		start.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Calendar end = new GregorianCalendar(year, 0, 1, 0, 0);
		start.add(Calendar.WEEK_OF_YEAR, week + 1);
		start.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		Lesson lStart = new Lesson(-1);
		lStart.setBeginn(start);
		Lesson lEnd = new Lesson(-1);
		lEnd.setBeginn(end);

		return lessons.subSet(lStart, true, lEnd, false);
	}

}
