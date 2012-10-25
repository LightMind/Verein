package plan;

import java.util.SortedSet;

import util.SortedObservingSet;

public class Teacher extends Person {
	SortedSet<Lesson> lessons = new SortedObservingSet<Lesson>();

	public Teacher(String name, int id) {
		super(name, id);
	}

	public void assign(Lesson l) {
		lessons.add(l);
	}

	public void unassign(Lesson l) {
		lessons.remove(l);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}