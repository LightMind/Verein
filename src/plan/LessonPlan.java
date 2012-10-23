package plan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Observer;

public class LessonPlan implements Observer<Lesson>{
	public List<Lesson> lessons = new ArrayList<Lesson>();

	//public List<Person> persons = new ArrayList<Person>();
	//public List<Horse> horses = new ArrayList<Horse>();
	//public List<Place> places = new ArrayList<Place>();
	//public List<LessonType> lessonTypes = new ArrayList<LessonType>();

	@Override
	public void notify(Lesson source) {
		Collections.sort(lessons);
	}
	
	public void addLesson(Lesson newLesson){
		int index = Collections.binarySearch(lessons, newLesson);
		if ( index < 0){
			int corrected = (index+1)*(-1);
			lessons.add(corrected,newLesson);
			return;
		}
		if ( !lessons.get(index).equals(newLesson)){
			lessons.add(index,newLesson);
			return;
		}
		// the lesson is already in the list
	}
	
}
