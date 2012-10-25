package plan;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;

import util.SortedObservingSet;

public class LessonType {
	public final int id;
	public String type;
	public NavigableSet<Lesson> lessons = new SortedObservingSet<Lesson>();

	public LessonType(String t, int id) {
		type = t;
		this.id = id;
	}

	// mutators //

	public void assign(Lesson l) {
		lessons.add(l);
	}

	public void unassign(Lesson l) {
		lessons.remove(l);
	}

	// accessors //

	public String getType() {
		return type;
	}

	public List<Lesson> getLessons() {
		return new ArrayList<Lesson>(lessons);
	}

	public int numberOfStudents() {
		int counter = 0;
		for (Lesson l : lessons) {
			counter += l.numberOfStudents();
		}
		return counter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LessonType other = (LessonType) obj;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}