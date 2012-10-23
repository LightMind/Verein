package plan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Assorted;
import util.Observer;

public class Place implements Observer<Lesson>{
	public String name = "";
	public List<Lesson> lessons = new ArrayList<Lesson>();
	public final int id;

	public Place(String n, int id) {
		name = n;
		this.id = id;
	}

	// mutators //

	public boolean assign(Lesson l) {
		int index = Collections.binarySearch(lessons, l);
		if (index < 0) {
			lessons.add((-1) * (index + 1), l);
			return true;
		}
		return false;
	}

	public boolean unassign(Lesson l) {
		int index = Collections.binarySearch(lessons, l);
		if (index < 0) {
			return false;
		}
		lessons.remove(index);
		return true;
	}

	// accessors //

	public String getName() {
		return name;
	}

	public List<Lesson> getLessons() {
		return new ArrayList<Lesson>(lessons);
	}

	@Override
	public void notify(Lesson source) {
		Collections.sort(lessons);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Place other = (Place) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Place [lessons=" + Assorted.renderList(lessons) + ", name=" + name + "]";
	}

}