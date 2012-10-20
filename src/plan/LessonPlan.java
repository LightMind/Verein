package plan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Observer;

public class LessonPlan implements Observer<Lesson>{
	public List<Lesson> lessons = new ArrayList<Lesson>();
	public List<Person> persons = new ArrayList<Person>();
	public List<Horse> horses = new ArrayList<Horse>();
	public List<Place> places = new ArrayList<Place>();
	public List<LessonType> lessonTypes = new ArrayList<LessonType>();
	
	@Override
	public void notify(Lesson source) {
		Collections.sort(lessons);
	}
}
