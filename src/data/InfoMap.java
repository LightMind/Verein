package data;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import plan.*;

public class InfoMap {
	private static Map<Integer, Horse> horses = new HashMap<Integer, Horse>();
	private Map<Integer, Person> people = new HashMap<Integer, Person>();
	private Map<Integer, Student> students = new HashMap<Integer, Student>();
	private Map<Integer, Teacher> teachers = new HashMap<Integer, Teacher>();
	private Map<Integer, LessonType> types = new HashMap<Integer, LessonType>();
	private Map<Integer, Place> places = new HashMap<Integer, Place>();
	private static Map<Integer, Lesson> lessons = new HashMap<Integer, Lesson>();

	public static Lesson createLesson(Calendar start, Calendar end) {
		int id = lessons.size();
		Lesson l = new Lesson(id, start);
		l.setEnd(end);
		lessons.put(id, l);
		return l;
	}

}
